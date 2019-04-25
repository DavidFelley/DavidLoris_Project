package com.example.davidloris_project.Fragment;

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

import com.example.davidloris_project.R;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPwdFragment extends Fragment {

    private EditText editTextemail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_reset_pwd, container, false);

        Button button = v.findViewById(R.id.btnSendEmail);
        button.setOnClickListener(sendEmailClick);
        editTextemail = v.findViewById(R.id.emailField);

        Button backLogin = v.findViewById(R.id.buttonLogin);
        backLogin.setOnClickListener(backLoginClick);

        return v;
    }

    View.OnClickListener sendEmailClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String email = editTextemail.getText().toString();

            if(email.trim().isEmpty() || !isEmailValid(email))
            {
                editTextemail.setError("Invalid email");
                editTextemail.requestFocus();
                return;
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email);
            Toast.makeText(getActivity(), "Email sent", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new LoginFragment()).commit();
        }
    };

    View.OnClickListener backLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.login_container, new LoginFragment()).commit();
        }
    };

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
