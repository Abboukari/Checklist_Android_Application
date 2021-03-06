package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.example.apeptodaygroep4.Models.Checkers;
import com.example.apeptodaygroep4.Models.Task;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class ShowTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        TextView textTitle = findViewById(R.id.showTaskDetailsTitle);
        TextView textDescription = findViewById(R.id.showTaskDetailsDescription);
        TextView textLabel = findViewById(R.id.showTaskDetailsLabel);
        TextView textDate = findViewById(R.id.showTaskDetailsTime);

        Task task = (Task) getIntent().getSerializableExtra("TaskDetail");
        Checkers checkers = new Checkers();

        String title = "Title: " + task.getTitle();
        String description = "Description: " + task.getDescription();
        String label = "Label: " + task.uIdLabel;
        String showDate;
        Date date = task.getDateTime();
        boolean datePassed = checkers.checkIfDateHasPassedDate(date);

        if (datePassed) {
            showDate = "You missed your deadline!";
        } else {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.MEDIUM, Locale.GERMAN);
            showDate = "Due date: " + formatter.format(date);
        }

        textTitle.setText(title);
        textDescription.setText(description);
        textLabel.setText(label);
        textDate.setText(showDate);
    }

}
