package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apeptodaygroep4.R;

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


    public void addTaskToDb(View view){
        //TODO:  add task to DB if all fields are filled
        //TODO: Snackbar task is added plus undo option
    }

    //TODO: attach Floating action button to label list
}