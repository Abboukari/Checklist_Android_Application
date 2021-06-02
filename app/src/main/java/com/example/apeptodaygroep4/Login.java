package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //TODO: get from user database and check if valid

    public void loginBtn(View view){
        Intent intent = new Intent(getApplicationContext(), MainMenuUser.class);

        EditText userName = findViewById(R.id.editTextUserName);
        EditText password = findViewById(R.id.editTextTextPassword);
        String name = userName.getText().toString();
        String pass = password.getText().toString();

        if(true){
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Wrong combination", Toast.LENGTH_LONG);
        }


    }

    public boolean checkIfValid(String name, String pass){
        boolean isValid= false;





        


        return isValid;
    }
}