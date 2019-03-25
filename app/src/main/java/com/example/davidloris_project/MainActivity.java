package com.example.davidloris_project;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.Local.MyDatabase;
import com.example.davidloris_project.Model.User;
import com.example.davidloris_project.ViewModel.UserVM;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm = getSupportFragmentManager();
    public static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "tabUser").allowMainThreadQueries().build();

        if (savedInstanceState == null) {
            fm.beginTransaction().replace(R.id.login_container, new LoginFragment()).commit();
        }
    }
/*
    public void Login(View view) {

        if (displayedFragment.getClass().getSimpleName().equals("SignInFragment")) {
            displayedFragment = new LoginFragment();
            fm.beginTransaction().replace(R.id.login_container, displayedFragment).commit();

        } else if (controleLogin()) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
*/
}
