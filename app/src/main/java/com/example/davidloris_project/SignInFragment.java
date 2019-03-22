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

    private EditText UserName, PassWord1, PassWord2;
    private Button Signin;
    private UserVM userVM;


    public SignInFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        userVM  = ViewModelProviders.of(this).get(UserVM.class);

        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        UserName = view.findViewById(R.id.usernameField);
        PassWord1 = view.findViewById(R.id.passwordField);
        PassWord2 = view.findViewById(R.id.passwordConfirmField);
        Signin = view.findViewById(R.id.buttonSignin);

        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = UserName.getText().toString();

                String password2 = PassWord2.getText().toString();

                Log.d("MESSAGE", UserName.getText().toString());
                Log.d("MESSAGE", PassWord2.getText().toString());

                User user = new User(userName,password2);


                userVM.insert(user);

                Toast.makeText(getActivity(), "User added successfully",Toast.LENGTH_SHORT).show();

                UserName.setText("");
                PassWord1.setText("");
                PassWord2.setText("");

            }
        });

        return view;
    }
}
