package com.example.davidloris_project.Activity;

import android.content.ClipData;
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
import com.example.davidloris_project.ViewModel.AnswerVM;

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

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_IDMESSAGE)) {
            setTitle("Edit Answer");
            editTextContent.setText(intent.getStringExtra(EXTRA_MESSAGE));
        } else
            setTitle("New Answer");
    }

    private void saveAnswer() {
        String message = editTextContent.getText().toString();

        if (message.trim().isEmpty()) {
            Toast.makeText(this, "Message is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_MESSAGE, message);

        int id = getIntent().getIntExtra(EXTRA_IDMESSAGE, -1);

        if (id != -1)
            data.putExtra(EXTRA_IDMESSAGE, id);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int id = getIntent().getIntExtra(EXTRA_IDMESSAGE, -1);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_subject_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.delete_subject);

        if (id != -1)
            menuItem.setVisible(true);

        return true;
    }

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

    private void deleteAnswer() {
        
        Intent data = new Intent();

        int id = getIntent().getIntExtra(EXTRA_IDMESSAGE, -1);
        
        if (id != -1)
            data.putExtra(EXTRA_IDMESSAGE, id);

        data.putExtra(EXTRA_DELETE_MESSAGE, 4);
        setResult(RESULT_OK, data);
        finish();
    }
}
