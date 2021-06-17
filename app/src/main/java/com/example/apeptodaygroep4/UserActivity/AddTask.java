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
import com.example.apeptodaygroep4.Models.Checkers;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.R;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddTask extends AppCompatActivity {
    private int tYear;
    private int tMonth;
    private int tDay;
    private int tHour;
    private int tMinute;
    private int userId;
    private User user;
    private Task task = new Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        user = (User) getIntent().getSerializableExtra("User");
        userId = (int) getIntent().getSerializableExtra("userId");
    }



    public void buttonDateTimePickerDialog(View view) {
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
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));


        TimePickerDialog timePicker = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.i(TAG, "Time chosen: " + hourOfDay + ":" + minute);
                        tHour = hourOfDay;
                        tMinute = minute;
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);

        datePicker.setTitle("Choose a Date");
        datePicker.show();
        timePicker.setTitle("Choose a time");
        timePicker.show();
    }


    public void addTaskToDb(View view) {

        Checkers checkers= new Checkers();

        EditText taskTitle = findViewById(R.id.editTextTitle);
        EditText taskDiscription = findViewById(R.id.editTextDiscription);
        Calendar myCalander = new GregorianCalendar(tYear, tMonth, tDay, tHour, tMinute);

        Intent toHomeIntent = new Intent(getApplicationContext(), HomeActivity.class);

        user = (User) getIntent().getSerializableExtra("User");
        userId = user.getId();

        boolean dateHasPassed = checkers.checkIfDateHasPassed(myCalander);
        if (dateHasPassed) {
            Toast.makeText(getApplicationContext(), "the date you picked is in the past", Toast.LENGTH_LONG).show();
        } else {
            String titleTask = taskTitle.getText().toString();
            String descriptionTask = taskDiscription.getText().toString();
            Date dueDateTask = myCalander.getTime();

            boolean checkIfFilled = checkers.checkEditFields(titleTask, descriptionTask);
            if (checkIfFilled){


                task.setuIdUser(userId);
                task.setTitle(titleTask);
                task.setDescription(descriptionTask);
                task.setDateTime(dueDateTask);

                UserDatabase.getExecutor().execute(() -> {
                    UserDatabase.getDatabase(getApplicationContext()).taskDao().addTask(task);
                    task = (Task) getIntent().getSerializableExtra("FilledLabelTask");//TODO: deze alleen toevoegen als label niet null is??
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Your task has been added", Toast.LENGTH_SHORT).show());
                    toHomeIntent.putExtra("User", user);

                    startActivity(toHomeIntent);

                });
            } else {
                Toast.makeText(getApplicationContext(), "Not all fields are filled", Toast.LENGTH_SHORT).show();
            }



        }
    }

    public void addLabel(View view) {
        Intent intent = new Intent(getApplicationContext(), AddLabel.class);
        user = (User) getIntent().getSerializableExtra("User");
        intent.putExtra("User", user);
        intent.putExtra("Task", task);
        startActivity(intent);
    }

}