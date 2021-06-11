package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

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

    private Integer taskId;
    private User user;
    Task task;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        //TODO: get uId from MainUserMenu
        user = (User) getIntent().getSerializableExtra("User");
        taskId = (Integer) getIntent().getSerializableExtra("taskId");
        UserDatabase.getExecutor().execute(()->{
                    task = UserDatabase.getDatabase(getApplicationContext()).taskDao().getTask(taskId);
                });

        EditText taskTitle = findViewById(R.id.editTASKTitle);
        EditText taskDiscription = findViewById(R.id.editTASKDiscription);
        Calendar myCalander = new GregorianCalendar(tYear,tMonth,tDay,tHour,tMinute);

        //TODO: set text in fields
        String title = task.getTitle();
        String discription = task.getDescription();
        Date date = task.getDateTime();


        taskTitle.setText(title);
        taskDiscription.setText(discription);
        myCalander.setTime(date);


    }





    // TODO: delete fields BUTTON
    public void deleteFieldBtn(View view){
        //TODO: functie toevoegen
        Intent toHomeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        toHomeIntent.putExtra("User", user);
    }




    // TODO: go to make label activity
    public void addLabel(View view){
        Intent intent = new Intent(getApplicationContext(),Label.class);
        startActivity(intent);
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


    public void updateFieldBtn(View view){

        EditText taskTitle = findViewById(R.id.editTASKTitle);
        EditText taskDiscription = findViewById(R.id.editTASKDiscription);
        Calendar myCalander = new GregorianCalendar(tYear,tMonth,tDay,tHour,tMinute);


        String titleTask = taskTitle.getText().toString();
        String descriptionTask = taskDiscription.getText().toString();
        Date dueDateTask = myCalander.getTime();

        Intent toHomeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        toHomeIntent.putExtra("User", user);



        //TODO:  add TEST for IfElse
        if (titleTask.equals("") || descriptionTask.equals("")|| dueDateTask == null){
            Toast.makeText(getApplicationContext(), "not all fields are filled", Toast.LENGTH_SHORT).show();
        } else {
            Task task = new Task(user.getId(),titleTask,descriptionTask,dueDateTask);

            UserDatabase.getExecutor().execute(()->{
                UserDatabase.getDatabase(getApplicationContext()).taskDao().addTask(task);//TODO: update not add
                runOnUiThread(()-> Toast.makeText(getApplicationContext(), "Your task has been added", Toast.LENGTH_SHORT).show());//UiThread andere draad

                startActivity(toHomeIntent);

            });
        }
    }
}