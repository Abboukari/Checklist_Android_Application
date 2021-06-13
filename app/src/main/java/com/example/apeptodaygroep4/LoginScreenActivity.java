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

import java.util.List;

public class LoginScreenActivity extends AppCompatActivity {
    User user;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_activity);
    }

    public void findUser(View view) {

        EditText editEmail = findViewById(R.id.EnterLoginEmailAddress);
        EditText editPassword = findViewById(R.id.enterLoginPassword);
        email = editEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();

        UserDatabase.getExecutor().execute(() -> {
            user = UserDatabase.getDatabase(getApplicationContext()).getUserDao().getUser(email, password);
            if (user != null) {
                Intent intent = new Intent(LoginScreenActivity.this, HomeActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
                finish();
            } else {
                runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), "incorrect username or password", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}
