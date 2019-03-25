package com.example.davidloris_project;

import android.arch.lifecycle.ViewModelProviders;
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

import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.ViewModel.SubjectVM;
import com.example.davidloris_project.ViewModel.UserVM;

public class SignInFragment extends Fragment {

    private Button signin;
    private UserVM userVm;
    private EditText username;
    private EditText login;
    private EditText confirmlog;
    private String user;
    private String log;
    private String pass;
    private User userObject;


    public SignInFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        userVm = ViewModelProviders.of(this).get(UserVM.class);

        username = view.findViewById(R.id.usernameField);
        login = view.findViewById(R.id.passwordConfirmField);
        confirmlog = view.findViewById(R.id.passwordField);

        signin = view.findViewById(R.id.buttonSignin);

        signin.setOnClickListener(myhandler);


        return view;
    }

    View.OnClickListener myhandler = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            user = username.getText().toString();
            log = login.getText().toString();
            pass = confirmlog.getText().toString();

            Log.d("Username : ", user);
            Log.d("Login : ", log);

            userObject = new User(user, log);

            if (userObject != null)
            {
                userVm.insert(userObject);


                username.setText("");
                login.setText("");
                confirmlog.setText("");

                Toast.makeText(getActivity(), "User insertion good !!!", Toast.LENGTH_SHORT).show();

            }else
            {
                Toast.makeText(getActivity(), "probleme user insertion", Toast.LENGTH_SHORT).show();

            }




        }
    };


}
