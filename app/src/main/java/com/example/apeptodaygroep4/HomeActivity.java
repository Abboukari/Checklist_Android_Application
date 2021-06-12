package com.example.apeptodaygroep4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.apeptodaygroep4.Dao.TaskDao;
import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.DoneTask;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.UserActivity.AddTask;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private User user;
    private TextView userName;
    private int userId;
    private ArrayList<Task> tasks;
    private ArrayAdapter<Task> adapter;
    private ListView listView;
    private Task task;
    DoneTask doneTask = new DoneTask();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (User) getIntent().getSerializableExtra("User");
        userName = findViewById(R.id.displayUserName);

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
            userId = user.getId();
            tasks = new ArrayList<Task>(
                    UserDatabase
                            .getDatabase(getApplicationContext())
                            .taskDao()
                            .getTilteTasks(userId)
            );

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
            //almost
            UserDatabase.getExecutor().execute(() -> {
                task = UserDatabase.getDatabase(getApplicationContext())
                        .taskDao().getTask(userId);

                doneTask.setDoneUserIdTask(task.getuIdTask());
                doneTask.setDoneTitle(task.getTitle());
                doneTask.setDoneDescription(task.getDescription());
                doneTask.setDoneUserIdUser(task.getuIdUser());

                UserDatabase.getDatabase(getApplicationContext()).doneTaskDao().addTaskDone(doneTask);

                deleteTaskClick(task);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Good job!", Toast.LENGTH_SHORT).show();
                    tasks.remove(info.position);
                    adapter.notifyDataSetChanged();
                });
            });

            return true;
        } else if (item.getItemId() == R.id.deletedTaskAction) {
            // DONE
            UserDatabase.getExecutor().execute(() -> {

                task = UserDatabase.getDatabase(getApplicationContext())
                        .taskDao().getTask(userId);
                // Delete task from user database
                deleteTaskClick(task);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Tasks Deleted", Toast.LENGTH_SHORT).show();
                    tasks.remove(info.position);
                    adapter.notifyDataSetChanged();
                });
            });
            return true;

        } else if (item.getItemId() == R.id.editTaskAction) {
            Toast.makeText(this, "Edit Task", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.showTask) {
            Toast.makeText(this, "Moving to Task Screen", Toast.LENGTH_SHORT).show();
        } else {
            return false;
        }
        return super.onContextItemSelected(item);
    }

    public void deleteTaskClick(final Task task) {
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