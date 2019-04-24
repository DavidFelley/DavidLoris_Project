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

public class AddEditAnswerActivity extends AppCompatActivity {
    public static final String EXTRA_IDMESSAGE = "com.example.davidloris_project.EXTRA_IDMESSAGE";
    public static final String EXTRA_MESSAGE = "com.example.davidloris_project.EXTRA_MESSAGE";
    public static final String EXTRA_DELETE_MESSAGE = "com.example.davidloris_project.EXTRA_DELETE_MESSAGE";
    private EditText editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        EditText editTextTitle = findViewById(R.id.text_view_title);
        editTextTitle.setVisibility(View.GONE);
        editTextContent = findViewById(R.id.text_view_message);

        Intent intent = getIntent();
        /* Check for the title of the activity */
        if (intent.hasExtra(EXTRA_IDMESSAGE)) {
            setTitle("Edit Answer");
            editTextContent.setText(intent.getStringExtra(EXTRA_MESSAGE));
        } else
            setTitle("New Answer");
    }

    /* Save the answer*/
    private void saveAnswer() {
        String message = editTextContent.getText().toString();

        /* Check if the field is not empty and not filled with spaces */
        if (message.trim().isEmpty()) {
            Toast.makeText(this, "Message is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_MESSAGE, message);

        /* Check if we are here to edit or to create a new answer*/
        String id = getIntent().getStringExtra(EXTRA_IDMESSAGE);


        /* if we come to edit we have pass the id of the answer and we need to give it back */
        if (id != null)
            data.putExtra(EXTRA_IDMESSAGE, id);

        setResult(RESULT_OK, data);
        finish();
    }

    /* Menu creator for the two buttons save and delete */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String id = getIntent().getStringExtra(EXTRA_IDMESSAGE);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_subject_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.delete_subject);
        /* if we come to create a new note the delete button is not visible */
        if (id != null)
            menuItem.setVisible(true);

        return true;
    }

    /* Listener for the two buttons in menu*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_subject:
                saveAnswer();
                return true;
            case R.id.delete_subject:
                deleteAnswer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* delete method linked to button */
    private void deleteAnswer() {
        Intent data = new Intent();

        String id = getIntent().getStringExtra(EXTRA_IDMESSAGE);

        if (id != null)
            data.putExtra(EXTRA_IDMESSAGE, id);

        /* We send back the value for the OnResultMethod */
        data.putExtra(EXTRA_DELETE_MESSAGE, 4);
        setResult(RESULT_OK, data);
        finish();
    }
}
