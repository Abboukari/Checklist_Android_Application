package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apeptodaygroep4.UserActivity.AddTask;
import com.example.apeptodaygroep4.UserActivity.CompletedTask;
import com.example.apeptodaygroep4.UserActivity.EditTask;

public class MainMenuUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_user);
    }

    public void addTaskBtn(View view){
        Intent intent = new Intent(getApplicationContext(), AddTask.class);
        startActivity(intent);
    }

    public void CompletedTaskBtn(View view){
        Intent intent = new Intent(getApplicationContext(), CompletedTask.class);
        startActivity(intent);
    }

    //TODO: give uId to editTask if clicked on List
}