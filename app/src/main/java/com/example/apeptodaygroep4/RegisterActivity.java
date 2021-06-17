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
    private ArrayList<String> emailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserDatabase.getExecutor().execute(() -> {
            emailList = new ArrayList<>(
                    UserDatabase
                            .getDatabase(getApplicationContext())
                            .getUserDao()
                            .getAllEmailFromUsers()
            );
        });
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

        if (isPasswordCorrect(password,passwordCheck) && emailValid && !userName.isEmpty()) {

            for (int i = 0; i < emailList.size(); i++) {
                if (emailList.get(i).equals(email)) {
                    emailFound = true;
                    break;
                }
            }

            if (emailFound) {
                makeMeAToast("Email already exist");
                makeMeAToast("Please try again or move to login");
            } else {
                User user = new User(userName, password, email);
                UserDatabase.getExecutor().execute(() -> {
                    UserDatabase.getDatabase(getApplicationContext()).getUserDao().insert(user);

                    runOnUiThread(()->{
                        makeMeAToast("Registration Succesful");
                        Intent moveToLogin = new Intent(RegisterActivity.this, LoginScreenActivity.class);
                        startActivity(moveToLogin);
                    });
                });
            }
        }
        if (userName.isEmpty()) {
            makeMeAToast("Don't you have a name?");
        }
        if (!emailValid) {
            makeMeAToast("Email is not valid");
        }
        if (password.isEmpty()){
            makeMeAToast("Please make sure to fill in letter/numbers/specials");
        }
    }

    //Checks if email has an valid email pattern
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    public void makeMeAToast(String text){
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public boolean isPasswordCorrect(String password1, String password2){
        int lengthUserPassword = 6;

        if (!password1.equals(password2)){
            Toast.makeText(getApplicationContext(), "Password is not matching", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password1.length() <= lengthUserPassword){
            Toast.makeText(getApplicationContext(), "You need a longer password!", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "At least 6 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}