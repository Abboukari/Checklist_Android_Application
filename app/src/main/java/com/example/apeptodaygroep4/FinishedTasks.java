package com.example.apeptodaygroep4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.DoneTask;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;

import java.util.ArrayList;
import java.util.List;

public class FinishedTasks extends AppCompatActivity {

    private List<DoneTask> doneTasks;
    private ArrayAdapter<DoneTask> adapter;
    private User user;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_tasks);

        ListView listView = findViewById(R.id.doneTasksListView);

        UserDatabase.getExecutor().execute(()->{
            user = (User) getIntent().getSerializableExtra("User");
            userId = user.getId();

            doneTasks = new ArrayList<>(
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

    public void returnToHome (View view) {
        User user = (User) getIntent().getSerializableExtra("User");
        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
        intent.putExtra("User",user);
        startActivity(intent);
    }

    public void deleteDoneTasks(View view){
        UserDatabase.getExecutor().execute(()->{
            UserDatabase.getDatabase(getApplicationContext()).doneTaskDao().deleteAllData();
            runOnUiThread(()->{
                Toast.makeText(this, "All done tasks are deleted", Toast.LENGTH_SHORT).show();
                notifyAdaptar();
            });
        });

    }

    // TODO: Why is it not updating adaptar?
    public void notifyAdaptar(){
        adapter.notifyDataSetChanged();
    }
}