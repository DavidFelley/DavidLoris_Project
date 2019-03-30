package com.example.davidloris_project.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.R;

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

        setTitle("New Subject");
    }

    /* Insert subject */
    private void saveSubject() {
        String title = editTextTitle.getText().toString();
        String message = editTextContent.getText().toString();

        /* Control if the fields are not empty or filled with spaces */
        if (title.trim().isEmpty() || message.trim().isEmpty()) {
            Toast.makeText(this, "Title or message is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        /* Send back the data to HomeActivity.ListSubjectFragment */
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_MESSAGE, message);

        setResult(RESULT_OK, data);
        finish();
    }

    /* Menu creator */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.add_subject_menu, menu);
        return true;
    }

    /* Listener for the menu item */
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
