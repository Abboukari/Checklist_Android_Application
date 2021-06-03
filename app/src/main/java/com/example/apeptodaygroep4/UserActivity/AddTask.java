package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.apeptodaygroep4.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

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
                    }
                },cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));


        TimePickerDialog timePicker = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.i(TAG,"Time chosen: " + hourOfDay + ":" + minute);
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true);

        datePicker.setTitle("Choose a Date");
        datePicker.show();
        timePicker.setTitle("Choose a time");
        timePicker.show();

    }


    public void addTaskToDb(View view){
        //TODO:  add task to DB if all fields are filled
        //TODO: Snackbar task is added plus undo option
    }

    //TODO: attach Floating action button to label list
}