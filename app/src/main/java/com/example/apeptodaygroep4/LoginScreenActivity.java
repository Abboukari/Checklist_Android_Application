package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.User;

public class LoginScreenActivity extends AppCompatActivity {
    UserDao db;
    UserDatabase database;
    EditText editEmail, editPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_activity);

        editEmail = findViewById(R.id.EnterLoginEmailAddress);
        editPassword = findViewById(R.id.enterLoginPassword);
        buttonLogin = findViewById(R.id.buttonToLogin);

        database = Room.databaseBuilder(this, UserDatabase.class,"User")
                .allowMainThreadQueries()
                .build();
        db = database.getUserDao();


        buttonLogin.setOnClickListener(v -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            User user = db.getUser(email,password);

            if (user != null){
                Intent intent = new Intent(LoginScreenActivity.this,HomeActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "incorrect username or password", Toast.LENGTH_SHORT).show();
            }
        });

    }


}