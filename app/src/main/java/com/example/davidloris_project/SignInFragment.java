package com.example.davidloris_project;

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

import com.example.davidloris_project.Model.User;

public class SignInFragment extends Fragment {

    private EditText UserName, Email, PassWord1, PassWord2;
    private Button Signin;


    public SignInFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        UserName = view.findViewById(R.id.usernameField);
        Email = view.findViewById(R.id.emailField);
        PassWord1 = view.findViewById(R.id.passwordField);
        PassWord2 = view.findViewById(R.id.passwordConfirmField);
        Signin = view.findViewById(R.id.buttonSignin);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = UserName.getText().toString();

                String password2 = PassWord2.getText().toString();

                User user = new User();
                user.setUsername(userName);
                user.setPassword(password2);

                MainActivity.myDatabase.userDAO().insertUser(user);
                Toast.makeText(getActivity(), "User added successfully",Toast.LENGTH_SHORT).show();

                UserName.setText("");
                Email.setText("");
                PassWord1.setText("");
                PassWord2.setText("");

            }
        });

        return view;
    }
}
