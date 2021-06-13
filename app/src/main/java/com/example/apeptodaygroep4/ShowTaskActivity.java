package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apeptodaygroep4.Models.Task;

public class ShowTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        TextView textView = findViewById(R.id.showTaskDetails);
        String detailedTask = (String) getIntent().getSerializableExtra("Task");

        textView.setText(detailedTask);
    }
}