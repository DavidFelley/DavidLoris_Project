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
    private EditText login;
    private EditText password;
    private String log;
    private String pass;
    private View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_login, container, false);

        userVM = ViewModelProviders.of(this).get(UserVM.class);
        Button loginButton = v.findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(loginClick);

        login = v.findViewById(R.id.usernameField_login);
        password = v.findViewById(R.id.passwordField_login);

        log = login.getText().toString();
        pass = password.getText().toString();


        return v;
    }

    View.OnClickListener loginClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {



            if (controleLogin()) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "User or password incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void goToSignIn() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new SignInFragment()).commit();
    }

    public boolean controleLogin() {
        User user = userVM.getUserByName(log, pass);

        if (user != null) {
            return true;
        }
        return false;
    }


}
