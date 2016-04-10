package android.alcode.com.login.RegisrationActivity;

import android.alcode.com.login.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MOMANI on 2016/04/03.
 */
public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();

    /* A mFirebaseReference to the Firebase */
    Firebase mFirebaseRef;

    /* A dialog that is presented until the Firebase authentication finished. */
    private ProgressDialog mAuthProgressDialog;

    private Button mLoginPasswordButton;
    TextView mTextViewEmail;
    TextView mTextViewPassword;

    //communicator design pattern
    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        ActionBar ab = ((RegistrationActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setTitle(R.string.login_ab_title);

        super.onCreate(savedInstanceState);

        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
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

        mLoginPasswordButton = (Button) getActivity().findViewById(R.id.email_sign_in_button);
        mLoginPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithPassword(mTextViewEmail.getText().toString().trim(), mTextViewPassword.getText().toString().trim());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("WhereIAm", TAG);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                FragmentManager fm = LoginFragment.this.getActivity().getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void loginWithPassword(final String email, String password) {
        mAuthProgressDialog.show();
        if (email.isEmpty() || password.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.login_error_message)
                    .setTitle(R.string.login_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            mFirebaseRef.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    mAuthProgressDialog.hide();
                    // Authenticated successfully with payload authData
                   Map<String, Object> map = new HashMap<String, Object>();
                    map.put("email", email);
                    mFirebaseRef.child("users").child(authData.getUid()).child("userInfo").updateChildren(map);
                    if (mListener != null) {
                        mListener.onFragmentInteraction("logged_in");
                    }
                    Toast.makeText(getContext(), "go To main Activity", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(getActivity().this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
                }

                @Override
                public void onAuthenticationError(FirebaseError firebaseError) {
                    mAuthProgressDialog.hide();
                    // Authenticated failed with error firebaseError
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(firebaseError.getMessage())
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
    }

}
