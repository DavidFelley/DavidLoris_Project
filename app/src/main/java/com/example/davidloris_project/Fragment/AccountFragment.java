package com.example.davidloris_project.Fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidloris_project.Activity.HomeActivity;
import com.example.davidloris_project.AsyncTaskListener;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.R;
import com.example.davidloris_project.ViewModel.UserVM;

import static android.content.Context.MODE_PRIVATE;
import static com.example.davidloris_project.Fragment.LoginFragment.MY_PREFS_NAME;

public class AccountFragment extends Fragment {

    private View v;
    private UserVM userVM;
    private String nameUser;
    private TextView textUsername;
    private EditText editTextOldPasswd;
    private EditText editTextNewPasswd;
    private String oldPasswd;
    private String newPasswd;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_account, container, false);
        userVM = ViewModelProviders.of(this).get(UserVM.class);

        editTextNewPasswd = v.findViewById(R.id.newPassword);
        editTextOldPasswd = v.findViewById(R.id.ancienPassword);
        textUsername = v.findViewById(R.id.nameUser);

        //On récupère le login du user
        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        nameUser = prefs.getString("MY_PREFS_NAME",MY_PREFS_NAME);
        textUsername.setText(nameUser);

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

    public void controleUserPass() {

        newPasswd = editTextNewPasswd.getText().toString();
        oldPasswd = editTextOldPasswd.getText().toString();


       // faire methode du controle de l ancien mot de passe

        userVM.getUserByName(nameUser, oldPasswd, new AsyncTaskListener() {
            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "oldPassword incorrect", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess() {

                // faire methode qui va update dans la base de donnée le noueau mot de passe
                userVM.updateUserPasswd(nameUser, newPasswd);
                Toast.makeText(getActivity(), "password change", Toast.LENGTH_SHORT).show();



            }
        });


    }
}
