package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.UserActivity.AddTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private User user;
    private TextView userName;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (User) getIntent().getSerializableExtra("User");
        userName = findViewById(R.id.displayUserName);

        if (user != null){
            userName.setText("Welcome " + user.getUserName());
        }

        FloatingActionButton fabButton = findViewById(R.id.fabHome);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startTaskIntent = new Intent(HomeActivity.this, AddTask.class);
                startActivity(startTaskIntent);
            }
        });

    }

    public void logOut(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}