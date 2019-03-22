package com.example.davidloris_project;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Local.UserDAO;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.ViewModel.SubjectVM;
import com.example.davidloris_project.ViewModel.UserVM;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm = getSupportFragmentManager();
    private Fragment displayedFragment = null;
    private  EditText login;
    private EditText password;
    private UserVM userVM;
    private User user;


    public  static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "tabUser").allowMainThreadQueries().build();
        userVM = ViewModelProviders.of(this).get(UserVM.class);

        if(savedInstanceState == null) {
            displayedFragment = new LoginFragment();
            fm.beginTransaction().replace(R.id.login_container, displayedFragment).commit();
        }
    }

    public void Login(View view){


        login = (EditText) findViewById(R.id.usernameField);
        password = (EditText) findViewById(R.id.passwordField);

        user = userVM.getUserLogin(login.getText().toString(),password.getText().toString());


        Log.d("Myapp", login.getText().toString());
        Log.d("Myapp", password.getText().toString());




        if(displayedFragment.getClass().getSimpleName().equals("SignInFragment")  ){
            displayedFragment = new LoginFragment();
            fm.beginTransaction().replace(R.id.login_container, displayedFragment).commit();

        } else //if(user != null)
             {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);

             }

           //  else {
            //AlertDialog.Builder ErrorMsg = new AlertDialog.Builder(this);
            //ErrorMsg.setMessage("Erreur dans la saisie du login ").setTitle("Erreur");
           // ErrorMsg.create();
            //ErrorMsg.show();
        //}

    }
    public void signIn(View view)
    {

        displayedFragment = new SignInFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.login_container, displayedFragment).commit();


    }



}
