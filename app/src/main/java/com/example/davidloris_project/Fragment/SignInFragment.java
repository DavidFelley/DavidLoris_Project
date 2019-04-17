package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.Activity.HomeActivity;
import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.UserVM;

public class SignInFragment extends Fragment {

    private UserEntity user;
    private UserVM userVM;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    public SignInFragment() {

    }

    //We create the view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_signin, container, false);


        userVM = ViewModelProviders.of(this).get(UserVM.class);

        //All the informations about the signin
        editTextUsername = view.findViewById(R.id.usernameField);
        editTextPassword = view.findViewById(R.id.passwordField);
        editTextConfirmPassword = view.findViewById(R.id.passwordConfirmField);

        //We instancied the signin button
        Button signin = view.findViewById(R.id.buttonSignin);
        signin.setOnClickListener(signInClick);

        //We instancied the backlogin button
        Button backLogin = view.findViewById(R.id.buttonLogin);
        backLogin.setOnClickListener(backLoginClick);

        return view;
    }

    View.OnClickListener signInClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            createUser();
        }
    };

    View.OnClickListener backLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new LoginFragment()).commit();
        }
    };


    private void createUser() {

        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String confirmPassword = editTextConfirmPassword.getText().toString();

        if (username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
            Toast.makeText(getActivity(), "All fields must be completed", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getActivity(), "Passwords doesn't match", Toast.LENGTH_SHORT).show();
            return;
        }

        user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);

        userVM.createUser(user, new AsyncTaskListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(), "User created successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(User user) {

            }

            @Override
            public void onSuccess(UserEntity user) {

            }

            @Override
            public void onFailure(Exception e) {
                Log.d("Creation Client", "createClient: failure", e);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    // Method for Local Database -> Useless
    /*
    public void controlUser() {

        //Get all the informations from EditText
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String confirmPassword = editTextConfirmPassword.getText().toString();

        userVM.getUserLogin(username, password, new AsyncTaskListener() {
            @Override
            public void onFailure() {

                // Control if the fields are not empty
                if (username.trim().isEmpty() || password.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "All fields must be completed", Toast.LENGTH_SHORT).show();
                    return;
                }

                // control if the user doesn't already exist


                // control if the passwords match
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(getActivity(), "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                    return;
                }

                User newUser = new User(username, password);
                userVM.insert(newUser);

                Toast.makeText(getActivity(), "User registered", Toast.LENGTH_SHORT).show();

                FragmentManager f = getFragmentManager();
                FragmentTransaction t = f.beginTransaction();
                Fragment frag = new LoginFragment();
                t.replace(R.id.login_container,frag);
                t.commit();
                Toast.makeText(getActivity(), "User dont exist", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {

                editTextUsername.setText("");

                Toast.makeText(getActivity(), "User exist", Toast.LENGTH_SHORT).show();


            }
        });

    }
    */
}
