package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.R;

public class EditLabel extends AppCompatActivity {

    private Task task;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_label);

        task = (Task) getIntent().getSerializableExtra("Task");
        user = (User) getIntent().getSerializableExtra("User");

        String currentLabel = task.getuIdLabel();
        TextView currentLabelUser = findViewById(R.id.currentLabel);
        currentLabelUser.setText(currentLabel);

    }


    public void updateLabelFromTask(View view){

        EditText editText = findViewById(R.id.getNewLabelUpdateButton);
        String labelChange = editText.getText().toString().trim();

        if (labelChange.isEmpty()){
            Toast.makeText(this, "You did not fill anything in", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this,EditTask.class);
            Toast.makeText(this, "Label is updated", Toast.LENGTH_SHORT).show();
            intent.putExtra("NewLabel", labelChange);
            intent.putExtra("User",user);
            intent.putExtra("Task", task);
            startActivity(intent);
        }

    }
}