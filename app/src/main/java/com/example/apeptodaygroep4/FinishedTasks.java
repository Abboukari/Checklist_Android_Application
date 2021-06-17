package com.example.apeptodaygroep4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.DoneTask;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;

import java.util.ArrayList;


public class FinishedTasks extends AppCompatActivity {

    private ArrayList<DoneTask> doneTasks;
    private ArrayAdapter<DoneTask> adapter;
    private User user;
    private int userId;
    private Task task = new Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_tasks);

        user = (User) getIntent().getSerializableExtra("User");

        ListView listView = findViewById(R.id.doneTasksListView);

        UserDatabase.getExecutor().execute(() -> {
            user = (User) getIntent().getSerializableExtra("User");
            userId = user.getId();

            doneTasks = new ArrayList<DoneTask>(
                    UserDatabase.getDatabase(getApplicationContext())
                            .doneTaskDao()
                            .getTitleTasksDone(userId)
            );

            adapter = new ArrayAdapter<>(
                    getApplication(),
                    android.R.layout.simple_list_item_1,
                    doneTasks
            );
            runOnUiThread(() -> listView.setAdapter(adapter));
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donemenu, menu);

        menu.setHeaderTitle("Select Action");
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.UnDoTaskAction) {
            UserDatabase.getExecutor().execute(() -> {
                DoneTask doneTaskPosition = doneTasks.get(info.position);

                task.setuIdTask(doneTaskPosition.getDoneUserIdTask());
                task.setTitle(doneTaskPosition.getDoneTitle());
                task.setDescription(doneTaskPosition.getDoneDescription());
                task.setuIdUser(doneTaskPosition.getDoneUserIdUser());
                task.setuIdLabel(doneTaskPosition.getDoneUserIdLabel());
                task.setDateTime(doneTaskPosition.getDateTime());

                UserDatabase.getDatabase(getApplicationContext()).taskDao().addTask(task);

                deleteDoneTask(doneTaskPosition);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Task is now not done", Toast.LENGTH_SHORT).show();
                    doneTasks.remove(info.position);
                    adapter.notifyDataSetChanged();
                });

            });

            return true;
        } else if (item.getItemId() == R.id.deletedDoneTaskAction) {
            UserDatabase.getExecutor().execute(() -> {
                DoneTask doneTaskPosition = doneTasks.get(info.position);
                deleteDoneTask(doneTaskPosition);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Task is deleted", Toast.LENGTH_SHORT).show();
                    doneTasks.remove(info.position);
                    adapter.notifyDataSetChanged();
                });
            });
            return true;
        } else {
            return false;
        }
    }

    public void deleteDoneTask(DoneTask doneTask) {
        UserDatabase.getDatabase(getApplicationContext()).doneTaskDao().deleteDoneTask(doneTask);
    }

    public void returnToHome(View view) {
        User user = (User) getIntent().getSerializableExtra("User");
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

}