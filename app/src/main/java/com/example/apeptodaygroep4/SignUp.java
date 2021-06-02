package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    public void signUpBtn(View view){
        EditText userName = findViewById(R.id.editTextUserName);
        EditText password1 = findViewById(R.id.editTextTextPassword);
        EditText password2 = findViewById(R.id.editTextTextPasswordCheck);
        String name = userName.getText().toString();
        String pass1 = password1.getText().toString();
        String pass2 = password2.getText().toString();

        boolean correctInput = checkIfFieldsCorrect(name,pass1,pass2);


    }



    private boolean checkIfFieldsCorrect(String name, String pass1, String pass2){
        boolean fields;

        if (name.equals("") || pass1.equals("") || pass2.equals("")){
            fields = false;
            Toast.makeText(getApplicationContext(),"No field shall be left unwritten", Toast.LENGTH_LONG).show();
        }else if (!pass1.equals(pass2)){
            fields = false;
            Toast.makeText(getApplicationContext(),"just like my socks I need matching passwords", Toast.LENGTH_LONG).show();
        } else {
            fields = true;
        }
        return fields;
    }


}