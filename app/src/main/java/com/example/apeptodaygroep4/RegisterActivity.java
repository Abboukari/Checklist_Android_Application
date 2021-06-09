package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextUserName, editTextEmail, editTextPassword, editTextPasswoordCheck;
    private UserDao userDao;
    private ArrayList<String> emailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserDatabase.getExecutor().execute(()->{
            emailList = new ArrayList<String>(
                    UserDatabase
                    .getDatabase(getApplicationContext())
                    .getUserDao()
                    .getAllEmail()
            );
        });

        userDao = Room.databaseBuilder(this, UserDatabase.class, "User").
                allowMainThreadQueries().
                build().
                getUserDao();

    }

    public void addUser(View view) {

        editTextUserName = findViewById(R.id.RegisterEnterPersonName);
        editTextEmail = findViewById(R.id.RegisterEnterEmailAddress);
        editTextPassword = findViewById(R.id.RegisterEnterPassword);
        editTextPasswoordCheck = findViewById(R.id.editTextTextPassword2);

        String userName = editTextUserName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordCheck = editTextPasswoordCheck.getText().toString().trim();

        boolean emailValid = isValidEmail(email);
        boolean emailFound = false;
        int lengthUserPassword = 6;

        // Happy of onhappy senario?
        if (password.equals(passwordCheck) && password.length() >= lengthUserPassword && emailValid && !userName.isEmpty()) {
            List<String> emails = userDao.getAllEmail(); //TODO: je hebt dan al een arrayllist met de naam emailList
            //Check if email already exist in de database, if yes move to if statement, if no make new user
            for (int i = 0; i < emails.size(); i++) {
                if (emails.get(i).equals(email)) {
                    emailFound = true;
                    break;
                }
            }

            if  (emailFound) {
                Toast.makeText(this, "Email already exist", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Please try again or move to login", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User(userName, password, email);
                userDao.insert(user);
                Toast.makeText(getApplicationContext(), "Registration Succesful", Toast.LENGTH_SHORT).show();
                Intent moveToLogin = new Intent(RegisterActivity.this, LoginScreenActivity.class);
                startActivity(moveToLogin);
            }

        } if (password.length() < lengthUserPassword) {
            Toast.makeText(getApplicationContext(), "You need a longer password!", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "At least 6 characters!", Toast.LENGTH_SHORT).show();
        } if (!emailValid) {
            Toast.makeText(getApplicationContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
        } if (userName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Don't you have a name?", Toast.LENGTH_SHORT).show();
        } if (!password.equals(passwordCheck)){
            Toast.makeText(getApplicationContext(), "Password is not matching", Toast.LENGTH_SHORT).show();
        }
    }

    //Checks if email has an valid email pattern
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


}