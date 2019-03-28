package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Activity.HomeActivity;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.UserVM;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {

    private UserVM userVM;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private String username;
    private String password;
    private View v;

    static SharedPreferences.Editor editor;
    static String MY_PREFS_NAME = "TestName";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_login, container, false);

        userVM = ViewModelProviders.of(this).get(UserVM.class);

        editTextLogin = v.findViewById(R.id.usernameField_login);
        editTextPassword = v.findViewById(R.id.passwordField_login);

        Button loginButton = v.findViewById(R.id.buttonLogin_login);
        loginButton.setOnClickListener(loginClick);

        Button signinButton = v.findViewById(R.id.buttonSignin_login);
        signinButton.setOnClickListener(signInClick);

        return v;
    }

    View.OnClickListener loginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            controlLogin();

        }
    };

    View.OnClickListener signInClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new SignInFragment()).commit();
        }
    };



    public void controlLogin() {


        username = editTextLogin.getText().toString();
        password = editTextPassword.getText().toString();
        MY_PREFS_NAME = editTextLogin.getText().toString();

        userVM.getUserByName(username, password, new AsyncTaskListener() {
            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "User or password incorrect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {

                editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(MY_PREFS_NAME, username);
                editor.apply();

                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

            }
        });

    }


}
