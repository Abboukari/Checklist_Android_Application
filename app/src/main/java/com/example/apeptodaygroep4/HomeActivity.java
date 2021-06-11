package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.UserActivity.AddTask;

import com.example.apeptodaygroep4.UserActivity.EditTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity  {

    private static final String TAG = "HomeActivity";
    private User user;
    private TextView userName;
    private int userId;
    private ArrayList<Task> tasks;
    RecyclerView recyclerView;
    private Task task;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (User) getIntent().getSerializableExtra("User");
        userName = findViewById(R.id.displayUserName);

        if (user != null) {
            userName.setText("Welcome " + user.getUserName());
            userId = user.getId();
        }

        FloatingActionButton fabButton = findViewById(R.id.fabHome);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startTaskIntent = new Intent(HomeActivity.this, AddTask.class);
                startTaskIntent.putExtra("userId", userId);
                startTaskIntent.putExtra("User", user);
                startActivity(startTaskIntent);
            }
        });

        ListView listView = findViewById(R.id.listViewTask);

        UserDatabase.getExecutor().execute(()->{
            tasks = new ArrayList<Task>(
                    UserDatabase
                        .getDatabase(getApplicationContext())
                        .taskDao()
                        .getTaskList(userId)
            );

            CustomAdapter adaptert = new CustomAdapter(
                    getApplicationContext(),
                    R.layout.item_task,
                    tasks);

            ArrayAdapter<Task> adapter = new ArrayAdapter<>(
                    getApplication(),
                    android.R.layout.simple_list_item_1,
                    tasks);

            runOnUiThread(()-> listView.setAdapter(adapter));
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                task = tasks.get(position);
                Integer taskId = task.getuIdTask();
                Log.d(TAG,"you clickedz:" + task.getTitle());
                Toast.makeText(getApplicationContext(),
                        "you clicked on " + task.getTitle(),
                        Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), EditTask.class);
                intent.putExtra("User", user);
                intent.putExtra("taskId", taskId);

                startActivity(intent);
            }
        });


    }

    public void setCompletedStatus(View view){
    }

    public void setTitleClickToEdit(View view){
        Toast.makeText(getApplicationContext(),"you Clicked: " + view.getTag(),Toast.LENGTH_LONG).show();
    }


    public void logOut(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }



}