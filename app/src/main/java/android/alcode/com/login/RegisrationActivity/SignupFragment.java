package android.alcode.com.login.RegisrationActivity;

import android.alcode.com.login.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();

    private Button mLoginPasswordButton;
    TextView mTextViewEmail;
    TextView mTextViewPassword;
    TextView mTextViewPasswordAgain;
    TextView mTextViewUserName;
    Firebase mFirebaseRef;


    /* A dialog that is presented until the Firebase authentication finished. */
    private ProgressDialog mAuthProgressDialog;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance() {
        SignupFragment fragment = new SignupFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        ActionBar ab = ((RegistrationActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle(R.string.sign_up_using_email);
        super.onCreate(savedInstanceState);

        // retain this fragment
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_with_email, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFirebaseRef = new Firebase(getString(R.string.firebase_url));


        /* Setup the progress dialog that is displayed later when authenticating with Firebase */
        mAuthProgressDialog = new ProgressDialog(getContext());
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);

        mTextViewEmail = (TextView) getActivity().findViewById(R.id.email);
        mTextViewPassword = (TextView) getActivity().findViewById(R.id.password);
        mTextViewPasswordAgain = (TextView) getActivity().findViewById(R.id.password_again);
        mTextViewUserName= (TextView) getActivity().findViewById(R.id.name);

        mLoginPasswordButton = (Button) getActivity().findViewById(R.id.email_sign_in_button);
        mLoginPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupWithEmail(mTextViewEmail.getText().toString().trim(), mTextViewPassword.getText().toString().trim(), mTextViewPasswordAgain.getText().toString().trim()
                ,mTextViewUserName.getText().toString().trim());
            }
        });
    }

    private void SignupWithEmail(final String email,final String password, String passwordAgain,final String userName) {
        if (!passwordAgain.equals(password)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.signup_error_message_password)
                    .setTitle(R.string.signup_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        mAuthProgressDialog.show();
        if (password.isEmpty() || email.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(R.string.signup_error_message)
                    .setTitle(R.string.signup_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {

            // signup
            mFirebaseRef.createUser(email, password,new Firebase.ResultHandler() {
                @Override
                public void onSuccess() {
                    mAuthProgressDialog.hide();
                    //AuthData authData;


                    //Map<String, Object> map = new HashMap<String, Object>();
                   // map.put("email", email);
                 //   mFirebaseRef.child("users").child(mFirebaseRef.).child("userInfo").updateChildren(map);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.signup_success)
                            .setPositiveButton(R.string.login_button_label, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    fragmentManager.popBackStack();
                                    //fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentManager.findFragmentByTag(LoginFragment.TAG)).commit();
//                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    mAuthProgressDialog.hide();
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(firebaseError.getMessage())
                            .setTitle(R.string.signup_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                FragmentManager fm = SignupFragment.this.getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
