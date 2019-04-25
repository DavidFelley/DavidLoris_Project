package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidloris_project.Activity.HomeActivity;
import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.BaseApp;
import com.example.davidloris_project.Entity.UserEntity;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.R;
import com.example.davidloris_project.Repository.UserRepository;
import com.example.davidloris_project.ViewModel.UserVM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.davidloris_project.Fragment.LoginFragment.MY_PREFS_NAME;

public class AccountFragment extends Fragment {


    private TextView textUsername;
    private EditText editTextNewPwd;
    private EditText editTextConfirmPwd;
    private EditText editTextOldPwd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        //We get the different TextView in the layout
        editTextOldPwd = v.findViewById(R.id.oldPassword);
        editTextNewPwd = v.findViewById(R.id.newPassword);
        editTextConfirmPwd = v.findViewById(R.id.confirmPassword);
        textUsername = v.findViewById(R.id.nameUser);

        //We set the username
        textUsername.setText(MY_PREFS_NAME);

        //We instanciate the click of the button
        Button changePasswd = v.findViewById(R.id.btn_changePwd);
        changePasswd.setOnClickListener(changePwdClick);

        return v;
    }

    View.OnClickListener changePwdClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controleUserPass();
        }
    };

    //We check the UserPass
    public void controleUserPass() {

        UserRepository userRepository = ((BaseApp) getActivity().getApplication()).getUserRepository();

        String username = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String oldPwd = editTextOldPwd.getText().toString();
        String newPwd = editTextNewPwd.getText().toString();
        String confNewPwd = editTextConfirmPwd.getText().toString();

        if (oldPwd.trim().isEmpty() || oldPwd.length() < 6) {
            editTextOldPwd.setError("Old password incorrect");
            editTextOldPwd.setText("");
            editTextOldPwd.requestFocus();
            return;
        }

        userRepository.signIn(username, oldPwd, task -> {
            if (task.isSuccessful()) {

                if (newPwd.trim().isEmpty() || newPwd.length() < 6) {
                    editTextNewPwd.setError("Field required");
                    editTextNewPwd.setText("");
                    editTextConfirmPwd.setText("");
                    editTextNewPwd.requestFocus();
                    return;
                }

                if (!newPwd.equals(confNewPwd)) {
                    editTextNewPwd.setError("Passwords doesn't match");
                    editTextNewPwd.requestFocus();
                    editTextNewPwd.setText("");
                    editTextConfirmPwd.setText("");
                    return;
                }

                userRepository.updatePwd(newPwd, new AsyncTaskListener() {
                    @Override
                    public void onFailure(Exception e) {
                        editTextNewPwd.setError(e.getMessage());
                        editTextNewPwd.setText("");
                        editTextConfirmPwd.setText("");
                        editTextNewPwd.requestFocus();
                    }

                    @Override
                    public void onSuccess() {
                        editTextOldPwd.setText("");
                        editTextNewPwd.setText("");
                        editTextConfirmPwd.setText("");
                        Toast.makeText(getActivity(), "Password changed", Toast.LENGTH_SHORT).show();
                    }

                });

            }

        });
    }
}