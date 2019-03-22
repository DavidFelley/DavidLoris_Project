package com.example.davidloris_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class AddSubjectActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        editTextTitle = findViewById(R.id.text_view_title);
        editTextContent = findViewById(R.id.text_view_message);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("New Subject");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.add_subject_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_subject:
        }
        return super.onOptionsItemSelected(item);
    }
}
