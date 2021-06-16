package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        String title = task.getTitle();
        String description = task.getDescription();
        String label = task.uIdLabel;
        String showDate;
        Date date = task.getDateTime();
        boolean datePassed = checkDate(date);

        if (datePassed){
            showDate = "YOu misSed your deadline fuCkFacE!";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            showDate = formatter.format(date);
        }

        textTitle.setText(title);
        textDescription.setText(description);
        textLabel.setText(label);
        textDate.setText(showDate);
    }


    public boolean checkDate(Date date){
        boolean dateHasPassed = true;

        Date currentDate = new Date();

        if (date.compareTo(currentDate) > 0){
            dateHasPassed = false;
        }
        return dateHasPassed;
    }
}
