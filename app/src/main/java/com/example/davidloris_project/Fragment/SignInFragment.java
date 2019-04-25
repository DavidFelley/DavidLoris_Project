package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
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

import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.UserVM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment {

    private UserEntity user;
    private UserVM userVM;
    private EditText editTextEmail;
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
        editTextEmail = view.findViewById(R.id.emailField);
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

        final String email = editTextEmail.getText().toString();
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String confirmPassword = editTextConfirmPassword.getText().toString();

        if (username.trim().isEmpty()) {
            editTextUsername.setError("Username required");
            editTextUsername.requestFocus();
            return;
        }

        if(password.trim().isEmpty())
        {
            editTextPassword.setError("Field required");
            editTextPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextPassword.setError("Passwords doesn't match");
            editTextPassword.requestFocus();
            editTextPassword.setText("");
            editTextConfirmPassword.setText("");
            return;
        }

        user = new UserEntity();
        user.setUsername(username);


        userVM.createUser(email, password, user, new AsyncTaskListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getActivity(), "User created successfully, please verify your email", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.sendEmailVerification();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new LoginFragment()).commit();
            }

            @Override
            public void onFailure(Exception e) {

                if (e.getClass().getSimpleName().equals("FirebaseAuthInvalidCredentialsException")) {
                    editTextEmail.setError(e.getMessage());
                    editTextEmail.requestFocus();
                    return;
                }

                if (e.getClass().getSimpleName().equals("FirebaseAuthUserCollisionException")) {
                    editTextEmail.setError(e.getMessage());
                    editTextEmail.requestFocus();
                    return;
                }

                if (e.getClass().getSimpleName().equals("FirebaseAuthWeakPasswordException")) {
                    editTextPassword.setError(e.getMessage());
                    editTextPassword.requestFocus();
                }
            }
        });
    }
}
