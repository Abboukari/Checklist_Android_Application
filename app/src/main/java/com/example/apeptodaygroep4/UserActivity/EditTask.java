package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.HomeActivity;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EditTask extends AppCompatActivity {
    private int tYear;
    private int tMonth;
    private int tDay;
    private int tHour;
    private int tMinute;
    private Task task = new Task();
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        task = (Task) getIntent().getSerializableExtra("Task");
        user = (User) getIntent().getSerializableExtra("User");

        EditText taskTitle = findViewById(R.id.editTitleUpdate);
        EditText taskDescription = findViewById(R.id.editTextDiscriptionUpdate);

        Calendar myCalender = new GregorianCalendar(tYear,tMonth,tDay,tHour,tMinute);

        String titel = task.getTitle();
        String description = task.getDescription();
        Date date = task.getDateTime();

        taskTitle.setText(titel);
        taskDescription.setText(description);
        myCalender.setTime(date);

    }


    public void buttonDateTimePickerDialog(View view){
        Calendar cal = new GregorianCalendar();
        final String TAG = "DIA_CAL";

        DatePickerDialog datePicker = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Log.i(TAG, "Date chosen: " + dayOfMonth + "-" + month + "-" + year);
                        tYear = year;
                        tMonth = month;
                        tDay = dayOfMonth;
                    }
                },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));


        TimePickerDialog timePicker = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.i(TAG,"Time chosen: " + hourOfDay + ":" + minute);
                        tHour = hourOfDay;
                        tMinute = minute;
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true);

        datePicker.setTitle("Choose a Date");
        datePicker.show();
        timePicker.setTitle("Choose a time");
        timePicker.show();
    }

    public void updateLabel(View view){
        Intent intent = new Intent(getApplicationContext(), EditLabel.class);
        user = (User) getIntent().getSerializableExtra("User");
        intent.putExtra("User",user);
        intent.putExtra("Task", task);
        startActivity(intent);
    }

    public void updateTaskToDatebase(View view){

        EditText updateTaskTitle = findViewById(R.id.editTitleUpdate);
        EditText updateTaskDiscription = findViewById(R.id.editTextDiscriptionUpdate);
        Calendar updateMyCalander= new GregorianCalendar(tYear,tMonth,tDay,tHour,tMinute);

        String titleTask = updateTaskTitle.getText().toString();
        String descriptionTask = updateTaskDiscription.getText().toString();
        Date dueDateTask = updateMyCalander.getTime();

        //TODO:  add task to DB if all fields are filled
        if (titleTask.isEmpty() || descriptionTask.isEmpty()|| dueDateTask == null){
            Toast.makeText(getApplicationContext(), "Not all fields are filled", Toast.LENGTH_SHORT).show();
        } else {

            String newLabelName = (String) getIntent().getSerializableExtra("NewLabel");

            task.editTask(task.getuIdTask(),titleTask,descriptionTask,dueDateTask,newLabelName);

            UserDatabase.getExecutor().execute(()->{
                UserDatabase.getDatabase(getApplicationContext()).taskDao().updateTask(task);

                runOnUiThread(()->{
                    Toast.makeText(getApplicationContext(), "Your task has been updated", Toast.LENGTH_SHORT).show();
                    Intent toHomeIntent = new Intent(getApplicationContext(), HomeActivity.class);
                    toHomeIntent.putExtra("User", user);
                    startActivity(toHomeIntent);
                });
            });
        }
    }
}