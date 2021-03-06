package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.HomeActivity;
import com.example.apeptodaygroep4.Models.Checkers;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class EditTask extends AppCompatActivity {
    private int tYear;
    private int tMonth;
    private int tDay;
    private int tHour;
    private int tMinute;
    private Task task = new Task();
    private User user;
    DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.MEDIUM, Locale.GERMAN);
    String dateText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        task = (Task) getIntent().getSerializableExtra("Task");
        user = (User) getIntent().getSerializableExtra("User");

        EditText taskTitle = findViewById(R.id.editTitleUpdate);
        EditText taskDescription = findViewById(R.id.editTextDiscriptionUpdate);
        TextView taskDateTime = findViewById(R.id.editTextDateTime);

        String title = task.getTitle();
        String description = task.getDescription();
        Date date = task.getDateTime();
        String label = task.getuIdLabel();
        dateText = "Due date: " + formatter.format(date);

        task.setuIdLabel(label);
        taskTitle.setText(title);
        taskDescription.setText(description);
        taskDateTime.setText(dateText);


    }


    public void buttonDateTimePickerDialog(View view) {
        Calendar cal = new GregorianCalendar();
        TextView taskDateTime = findViewById(R.id.editTextDateTime);


        final String TAG = "DIA_CAL";

        DatePickerDialog datePicker = new DatePickerDialog(this,
                (view1, year, month, dayOfMonth) -> {
                    Log.i(TAG, "Date chosen: " + dayOfMonth + "-" + month + "-" + year);
                    tYear = year;
                    tMonth = month;
                    tDay = dayOfMonth;
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));


        TimePickerDialog timePicker = new TimePickerDialog(this,
                (view12, hourOfDay, minute) -> {
                    Log.i(TAG, "Time chosen: " + hourOfDay + ":" + minute);
                    tHour = hourOfDay;
                    tMinute = minute;
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);

        datePicker.setTitle("Choose a Date");
        datePicker.show();
        timePicker.setTitle("Choose a time");
        timePicker.show();
        dateText = "";
        taskDateTime.setText(dateText);

    }

    public void updateLabel(View view) {
        Intent intent = new Intent(getApplicationContext(), EditLabel.class);
        user = (User) getIntent().getSerializableExtra("User");
        intent.putExtra("User", user);
        intent.putExtra("Task", task);
        startActivity(intent);
    }

    public void updateTaskToDatebase(View view) {

        Checkers checkers = new Checkers();
        String newLabelName = (String) getIntent().getSerializableExtra("NewLabel");

        EditText updateTaskTitle = findViewById(R.id.editTitleUpdate);
        EditText updateTaskDescription = findViewById(R.id.editTextDiscriptionUpdate);//
        Calendar updateMyCalender = new GregorianCalendar(tYear, tMonth, tDay, tHour, tMinute);//

        boolean dateHasPassed = checkers.checkIfDateHasPassed(updateMyCalender);

        if (dateHasPassed) {
            Toast.makeText(getApplicationContext(), "the date you picked is in the past", Toast.LENGTH_LONG).show();
        } else {
            String titleTask = updateTaskTitle.getText().toString();
            String descriptionTask = updateTaskDescription.getText().toString();
            Date dueDateTask = updateMyCalender.getTime();

            boolean checkIfAllIsFilled = checkers.checkEditFields(titleTask, descriptionTask);

            if (checkIfAllIsFilled && newLabelName == null) {
                String label = task.getuIdLabel();
                task.setuIdLabel(label);
                task.editTask(task.getuIdTask(), titleTask, descriptionTask, dueDateTask, label);

                UserDatabase.getExecutor().execute(() -> {
                    UserDatabase.getDatabase(getApplicationContext()).taskDao().updateTask(task);

                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Your task has been updated", Toast.LENGTH_SHORT).show();
                        Intent toHomeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                        toHomeIntent.putExtra("User", user);
                        startActivity(toHomeIntent);
                    });
                });
            } else if (checkIfAllIsFilled) {
                task.editTask(task.getuIdTask(), titleTask, descriptionTask, dueDateTask, newLabelName);
                UserDatabase.getExecutor().execute(() -> {
                    UserDatabase.getDatabase(getApplicationContext()).taskDao().updateTask(task);

                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Your task has been updated", Toast.LENGTH_SHORT).show();
                        Intent toHomeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                        toHomeIntent.putExtra("User", user);
                        startActivity(toHomeIntent);
                    });
                });
            } else {
                Toast.makeText(getApplicationContext(), "Not all fields are filled", Toast.LENGTH_SHORT).show();
            }

        }

    }

}