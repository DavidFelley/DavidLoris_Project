package com.example.davidloris_project;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Home extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
        R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    public void Actuality(View view) {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Automobile(View view) {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Game(View view) {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Health(View view) {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void IT(View view) {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Sexuality(View view) {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Sport(View view) {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }
}
