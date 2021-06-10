package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.UserActivity.AddTask;
import com.example.apeptodaygroep4.ui.tasks.TaskAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TaskAdapter taskAdapter;

    private User user;
    private TextView userName;
    private int userId;
    private ArrayList<Task> tasks;
    RecyclerView recyclerView;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*recyclerView = (RecyclerView) findViewById(R.id.recycler_view_tasks);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        UserDatabase.getExecutor().execute(() -> {
            tasks = new ArrayList<Task>(
                    UserDatabase
                            .getDatabase(getApplicationContext())
                            .taskDao()
                            .getTaskList(userId)
            );

            taskAdapter = new TaskAdapter(getApplicationContext(), tasks);
            recyclerView.setAdapter(taskAdapter);


        });*/

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
                startActivity(startTaskIntent);
            }
        });

        ListView listView = findViewById(R.id.listViewTask);
        UserDatabase.getExecutor().execute(()->{
            tasks = new ArrayList<Task>(
                    UserDatabase
                        .getDatabase(getApplicationContext())
                        .taskDao()
                        .getTilteTasks(userId)
            );

            ArrayAdapter<Task> adapter = new ArrayAdapter<>(
                    getApplication(),
                    android.R.layout.simple_list_item_1,
                    tasks);

            runOnUiThread(()-> listView.setAdapter(adapter));
        });
    }


    public void logOut(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}