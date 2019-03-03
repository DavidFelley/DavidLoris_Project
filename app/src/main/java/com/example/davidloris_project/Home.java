package com.example.davidloris_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void Actuality(View view)
    {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Automobile(View view)
    {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Game(View view)
    {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Healthy(View view)
    {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Informatique(View view)
    {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Sexuality(View view)
    {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }

    public void Sport(View view)
    {
        Intent intent = new Intent(this, HomeCategory.class);
        startActivity(intent);
    }
}
