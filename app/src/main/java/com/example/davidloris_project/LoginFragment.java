package com.example.davidloris_project;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.ViewModel.UserVM;


public class LoginFragment extends Fragment {

    private UserVM userVM;
    private EditText editTextLogin;
    private EditText editTextPassword;
    private String username;
    private String password;
    private View v;


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
            if (controlLogin()) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "User or password incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener signInClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new SignInFragment()).commit();
        }
    };

    public boolean controlLogin() {
        
        username = editTextLogin.getText().toString();
        password = editTextPassword.getText().toString();

        User user = userVM.getUserByName(username, password);

        if (user != null)
            return true;

        return false;
    }


}
