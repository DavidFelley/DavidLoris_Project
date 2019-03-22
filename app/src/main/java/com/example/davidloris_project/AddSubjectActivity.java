package com.example.davidloris_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddSubjectActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "com.example.davidloris_project.EXTRA_TITLE";
    public static final String EXTRA_MESSAGE = "com.example.davidloris_project.EXTRA_MESSAGE";

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

    private void saveSubject(){
        String title = editTextTitle.getText().toString();
        String message = editTextContent.getText().toString();

        if(title.trim().isEmpty() || message.trim().isEmpty()){
            Toast.makeText(this, "Title or message is empty", Toast.LENGTH_SHORT);
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_MESSAGE,message);

        setResult(RESULT_OK, data);
        finish();
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
                saveSubject();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
