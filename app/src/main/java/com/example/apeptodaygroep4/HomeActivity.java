package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apeptodaygroep4.Models.User;

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
    }

    public void logOut(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}