package android.alcode.com.login.RegisrationActivity;

import android.alcode.com.login.R;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by MOMANI on 2016/04/03.
 */
public class SignupOrLoginFragment extends Fragment {
    public static final String TAG = SignupOrLoginFragment.class.getSimpleName();


    private Button mLoginWithEmailButton;
    private Button mSignupWithEmailButton;
    private Button mRegisterLaterButton;

    //communicator design pattern
    private OnFragmentInteractionListener mListener;

    public SignupOrLoginFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PlusOneFragment.
     */
    public static SignupOrLoginFragment newInstance() {
        SignupOrLoginFragment fragment = new SignupOrLoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_ways, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLoginWithEmailButton = (Button) getActivity().findViewById(R.id.login_with_password);
        mLoginWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOperationToRegisterationActivity("login_with_email");
            }
        });
        mSignupWithEmailButton = (Button) getActivity().findViewById(R.id.signup_with_password);
        mSignupWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOperationToRegisterationActivity("register_with_email");
            }
        });
        mRegisterLaterButton = (Button) getActivity().findViewById(R.id.register_later_button);
        mRegisterLaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOperationToRegisterationActivity("register_later");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//because other fragments changed actoin bar
        ActionBar ab = ((RegistrationActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowHomeEnabled(false);
        ab.setTitle(R.string.registration_activity_label);
    }

    private void sendOperationToRegisterationActivity(String operation) {
        if (mListener != null) {
            mListener.onFragmentInteraction(operation);
        }
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


}
