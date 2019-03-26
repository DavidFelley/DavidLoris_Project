package com.example.davidloris_project.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.davidloris_project.Fragment.AccountFragment;
import com.example.davidloris_project.Fragment.InSubjectFragment;
import com.example.davidloris_project.Fragment.ListCategoryFragment;
import com.example.davidloris_project.Fragment.ListSubjectFragment;
import com.example.davidloris_project.R;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Fragment displayedFragment;
    private FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            displayedFragment = new ListCategoryFragment();
            fm.beginTransaction().replace(R.id.fragment_container, displayedFragment).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_about:
                fm.beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                break;
            case R.id.nav_account:
                fm.beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                break;
            case R.id.nav_recentPost:
                fm.beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void CategoryClick(View view) {

        /* Get the button name to know which category was clicked */
        String categoryName = ((Button)view).getText().toString();
        /* Pass the button name to the fragment to do the correct query and get the correct subjects */
        Bundle bundle = new Bundle();
        bundle.putString("CategoryName", categoryName);

        ListSubjectFragment fragment = new ListSubjectFragment();
        fragment.setArguments(bundle);

        fm.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    public void SubjectClick(View view){
        fm.beginTransaction().replace(R.id.fragment_container, new InSubjectFragment()).commit();
    }
}
