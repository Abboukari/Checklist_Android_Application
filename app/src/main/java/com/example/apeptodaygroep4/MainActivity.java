package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToRegisterActivity(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Please make sure to fill in your own email", Toast.LENGTH_SHORT).show();
    }

    public  void moveToLoginActivity(View view){
        Intent intent = new Intent(this, LoginScreenActivity.class);
        startActivity(intent);
    }

}