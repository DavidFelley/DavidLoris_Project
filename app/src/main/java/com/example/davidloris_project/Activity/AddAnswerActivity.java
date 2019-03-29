package com.example.davidloris_project.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidloris_project.R;

public class AddAnswerActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.davidloris_project.EXTRA_MESSAGE";
    private EditText editTextTitle;
    private EditText editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        editTextTitle = findViewById(R.id.text_view_title);
        editTextTitle.setVisibility(View.GONE);
        editTextContent = findViewById(R.id.text_view_message);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("New Answer");
    }

    private void saveAnswer() {
        String message = editTextContent.getText().toString();

        if (message.trim().isEmpty()) {
            Toast.makeText(this, "Message is empty", Toast.LENGTH_SHORT);
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_MESSAGE, message);
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
                saveAnswer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
