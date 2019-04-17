package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Activity.HomeActivity;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.UserVM;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment {


    private UserVM userVM;
    private EditText editTextLogin;
    private EditText editTextPassword;

    public static String MY_PREFS_NAME ;
    static int USER_ID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);

        userVM = ViewModelProviders.of(this).get(UserVM.class);

        //Get the informations about the login
        editTextLogin = v.findViewById(R.id.usernameField_login);
        editTextPassword = v.findViewById(R.id.passwordField_login);

        //Instancied the login button
        Button loginButton = v.findViewById(R.id.buttonLogin_login);
        loginButton.setOnClickListener(loginClick);

        //Instancied the signin button
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


    //We controle the login
    public void controlLogin() {

        //All the information that we need
        String username = editTextLogin.getText().toString();
        String password = editTextPassword.getText().toString();
        MY_PREFS_NAME = editTextLogin.getText().toString();

        userVM.getUserLogin(username, password, new AsyncTaskListener() {

            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "User or password incorrect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {

            }


            @Override
            public void onSuccess(User user) {

                USER_ID = user.getIdUser();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("message");

                myRef.setValue("Hello, World!");


                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

            }
        });

    }


}
