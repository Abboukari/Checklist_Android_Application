package com.example.apeptodaygroep4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.DoneTask;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.UserActivity.AddTask;

import com.example.apeptodaygroep4.UserActivity.EditTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<Task> tasks;
    private ArrayAdapter<Task> adapter;
    private User user;
    private int userId;
    private ListView listView;
    private final DoneTask doneTask = new DoneTask();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (User) getIntent().getSerializableExtra("User");
        TextView userName = findViewById(R.id.displayUserName);

        if (user != null) {
            userName.setText("Ready for ToDay " + user.getUserName() + "?");
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

        listView = findViewById(R.id.listViewTask);
        UserDatabase.getExecutor().execute(() -> {
            user = (User) getIntent().getSerializableExtra("User");

            tasks = new ArrayList<Task>(
                    UserDatabase
                            .getDatabase(getApplicationContext())
                            .taskDao()
                            .getAllDetailsFromTasks(user.getId())
            );

            Comparator<Task> taskComparator = Comparator.comparing(Task::getDateTime)
                    .thenComparing(Task::getTitle);
            tasks.sort(taskComparator);

            adapter = new ArrayAdapter<>(
                    getApplication(),
                    android.R.layout.simple_list_item_1,
                    tasks);
            runOnUiThread(() -> listView.setAdapter(adapter));
        });
        registerForContextMenu(listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tasksmenu, menu);

        menu.setHeaderTitle("Select Action");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.finishedTaskAction) {
            UserDatabase.getExecutor().execute(() -> {
                Task taskPosition = tasks.get(info.position);
                doneTask.setDoneUserIdTask(taskPosition.getuIdTask());
                doneTask.setDoneTitle(taskPosition.getTitle());
                doneTask.setDoneDescription(taskPosition.getDescription());
                doneTask.setDoneUserIdUser(taskPosition.getuIdUser());
                doneTask.setDoneUserIdLabel(taskPosition.getuIdLabel());
                doneTask.setDateTime(taskPosition.getDateTime());

                UserDatabase.getDatabase(getApplicationContext()).doneTaskDao().addTaskDone(doneTask);
                deleteTaskClick(taskPosition);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Good job!", Toast.LENGTH_SHORT).show();
                    tasks.remove(info.position);
                    adapter.notifyDataSetChanged();
                });
            });

            return true;
        } else if (item.getItemId() == R.id.deletedTaskAction) {
            UserDatabase.getExecutor().execute(() -> {
                Task taskPosition = tasks.get(info.position);
                deleteTaskClick(taskPosition);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show();
                    tasks.remove(info.position);
                    adapter.notifyDataSetChanged();
                });
            });

            return true;

        } else if (item.getItemId() == R.id.editTaskAction) {
            Toast.makeText(this, "Edit Task", Toast.LENGTH_SHORT).show();
            Task taskposition = tasks.get(info.position);

            Intent intent = new Intent(getApplicationContext(), EditTask.class);
            intent.putExtra("User", user);
            intent.putExtra("Task", taskposition);
            startActivity(intent);
            return true;

        } else if (item.getItemId() == R.id.showTask) {

            Task taskPosition = tasks.get(info.position);

            runOnUiThread(() -> {
                Intent intent = new Intent(this, ShowTaskActivity.class);
                intent.putExtra("TaskDetail", taskPosition);
                startActivity(intent);
            });
            return true;
        } else {
            return false;
        }
    }

    public void deleteTaskClick(Task task) {
        UserDatabase.getDatabase(getApplicationContext()).taskDao().deleteTask(task);
    }


    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void moveToDoneTask(View view) {
        Intent intent = new Intent(this, FinishedTasks.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

}