package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void goToLogin(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);

    }

    public void goToSignUp(View view){
        Intent intent = new Intent(getApplicationContext(),SignUp.class);
        startActivity(intent);

    }
}