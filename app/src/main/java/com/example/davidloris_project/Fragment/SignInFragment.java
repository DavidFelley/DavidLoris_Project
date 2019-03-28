package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.Activity.HomeActivity;
import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.UserVM;

public class SignInFragment extends Fragment {

    private UserVM userVm;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private String username;
    private String password;
    private String confirmPassword;
    private User userObject;
    private AsyncTask<String, Void, User> userControle;

    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        userVm = ViewModelProviders.of(this).get(UserVM.class);

        editTextUsername = view.findViewById(R.id.usernameField);
        editTextPassword = view.findViewById(R.id.passwordField);
        editTextConfirmPassword = view.findViewById(R.id.passwordConfirmField);

        Button signin = view.findViewById(R.id.buttonSignin);
        signin.setOnClickListener(signInClick);

        Button backLogin = view.findViewById(R.id.buttonLogin);
        backLogin.setOnClickListener(backLoginClick);

        return view;
    }

    View.OnClickListener signInClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();
            confirmPassword = editTextConfirmPassword.getText().toString();
            userControle = userVm.getUserByusername(username);


            /* Control if the fields are not empty */
            if (username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
                Toast.makeText(getActivity(), "All fields must be completed", Toast.LENGTH_SHORT).show();
                return;
            }

            /* control if the user doesn't already exist */



            /* control if the passwords match */
            if (!password.equals(confirmPassword)) {
                Toast.makeText(getActivity(), "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                return;
            }

            userObject = new User(username, password);
            userVm.insert(userObject);

            Toast.makeText(getActivity(), "User registered", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
        }
    };

    View.OnClickListener backLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new LoginFragment()).commit();
        }
    };



}
