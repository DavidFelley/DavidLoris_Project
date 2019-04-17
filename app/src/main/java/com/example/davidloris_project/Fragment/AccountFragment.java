package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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

import com.example.davidloris_project.Activity.HomeActivity;
import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.UserVM;

import static com.example.davidloris_project.Fragment.LoginFragment.MY_PREFS_NAME;

public class AccountFragment extends Fragment {


    private UserVM userVM;
    private String username;
    private TextView textUsername;
    private EditText editTextOldPasswd;
    private EditText editTextNewPasswd;
    private EditText editTextConfirmPasswd;
    private String newPasswd;
    private String confirmPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        userVM = ViewModelProviders.of(this).get(UserVM.class);

        //We get the different TextView in the layout
        editTextNewPasswd = v.findViewById(R.id.newPassword);
        editTextOldPasswd = v.findViewById(R.id.ancienPassword);
        editTextConfirmPasswd = v.findViewById(R.id.confirmPassword);
        textUsername = v.findViewById(R.id.nameUser);

        //We set the username
        textUsername.setText(MY_PREFS_NAME);

        //We instanciate the click of the button
        Button changePasswd = v.findViewById(R.id.btn_changePwd);
        changePasswd.setOnClickListener(changePasswdClick);

        return v;

    }

    View.OnClickListener changePasswdClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controleUserPass();
        }
    };

    //We check the UserPass
    public void controleUserPass() {

        String oldPasswd = editTextOldPasswd.getText().toString();
        newPasswd = editTextNewPasswd.getText().toString();
        confirmPassword = editTextConfirmPasswd.getText().toString();
        username = textUsername.getText().toString();

        userVM.getUserLogin(username, oldPasswd, new AsyncTaskListener() {


            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "Old password incorrect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {

            }


            @Override
            public void onSuccess(User user) {

                if (newPasswd.equals(confirmPassword)) {
                    userVM.updateUserPasswd(username, newPasswd);
                    Toast.makeText(getActivity(), "password changed", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Passwords doesn't match", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onSuccess(UserEntity user) {

            }
        });
    }
}
