package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Database.DatabaseUser;
import com.example.apeptodaygroep4.Models.User;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private ArrayList<User> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ListView listView = findViewById(R.id.testListView);

        DatabaseUser.getDbExecutor().execute(()->{
            user = new ArrayList<User>(
                    DatabaseUser
                    .getDatabaseUser(this)
                    .userDao()
                    .getAllUsers()
                    //.getUidFromUser()
            );

            ArrayAdapter<User> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    user
            );

            //adapter conecten aan listview
            runOnUiThread(()-> listView.setAdapter(adapter));
        });
    }

    //TODO: get from user database and check if valid

    public void loginBtn(View view){
        Intent intent = new Intent(getApplicationContext(), MainMenuUser.class);

        EditText userName = findViewById(R.id.editTextUserName);
        EditText password = findViewById(R.id.editTextTextPassword);
        String name = userName.getText().toString();
        String pass = password.getText().toString();

        boolean validation = checkIfValid(name,pass);

        if(validation){
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(),"Wrong combination", Toast.LENGTH_LONG).show();
        }


    }

    public boolean checkIfValid(String name, String pass){
        boolean isValid= false;

        for (int i = 0; i < user.size(); i++){
            if (name.equals(user.get(i).getUserName())){
                if (pass.equals(user.get(i).getPassword())){
                    isValid = true;
                    //get Uid and open database for that user
                }
            }
        }
        return isValid;
    }
}