package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.apeptodaygroep4.R;

public class EditTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        //TODO: get uId from MainUserMenu
        //TODO: set text in fields
    }



    // TODO: update fields BUTTON
    public void updateFieldBtn(View view){

    }

    // TODO: delete fields BUTTON
    public void deleteFieldBtn(View view){}


    // TODO: go to make label activity

    public void addLabel(View view){
        Intent intent = new Intent(getApplicationContext(),Label.class);
        startActivity(intent);
    }
}