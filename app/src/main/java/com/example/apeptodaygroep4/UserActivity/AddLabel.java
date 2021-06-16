package com.example.apeptodaygroep4.UserActivity;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.Label;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.R;

import java.util.ArrayList;

public class AddLabel extends AppCompatActivity {

    private ArrayList<Label> allUserLabels;
    private ArrayAdapter<Label> adapter;
    private User user;
    private int userId;
    private Task task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        user = (User) getIntent().getSerializableExtra("User");
        task = (Task) getIntent().getSerializableExtra("Task");

        ListView listView = findViewById(R.id.listViewLabels);

        UserDatabase.getExecutor().execute(() -> {

            user = (User) getIntent().getSerializableExtra("User");
            userId = user.getId();

            allUserLabels = new ArrayList<>(
                    UserDatabase.getDatabase(getApplicationContext())
                            .labelDao()
                            .getAllLabels(userId)
            );

            adapter = new ArrayAdapter<>(
                    getApplication(),
                    android.R.layout.simple_list_item_1,
                    allUserLabels
            );

            runOnUiThread(() -> {
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            });
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menulabels, menu);

        menu.setHeaderTitle("Choose a option");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getItemId() == R.id.SelectLabelAction) {

            UserDatabase.getExecutor().execute(() -> {
                task = (Task) getIntent().getSerializableExtra("Task");
                user = (User) getIntent().getSerializableExtra("User");
                userId = user.getId();

                Label label = allUserLabels.get(info.position);
                task.setuIdLabel(label.toString());

                runOnUiThread(() -> {
                    Intent intent = new Intent(this, AddTask.class);
                    intent.putExtra("FilledLabelTask", task);
                    intent.putExtra("User", user);
                    intent.putExtra("userId", userId);
                    startActivity(intent);
                    Toast.makeText(this, "Label is selected", Toast.LENGTH_SHORT).show();
                });
            });
            return true;
        } else if (item.getItemId() == R.id.deletedLabelAction) {
            UserDatabase.getExecutor().execute(() -> {
                Label label = allUserLabels.get(info.position);
                deleteLabel(label);

                runOnUiThread(() -> {
                    Toast.makeText(this, "Label Deleted", Toast.LENGTH_SHORT).show();
                    allUserLabels.remove(label);
                    adapter.notifyDataSetChanged();
                });
            });
            return true;
        } else if (item.getItemId() == R.id.EditLabelAction) {
            Label selectedLabel = allUserLabels.get(info.position);
            Intent intent = new Intent(getApplicationContext(), UpdateLabel.class);
            intent.putExtra("Label", selectedLabel);
            intent.putExtra("User", user);
            intent.putExtra("Task", task);

            startActivity(intent);
            return true;
        } else {
            return false;
        }
    }

    public void addLabelToArrayList(View view) {

        EditText labelName = findViewById(R.id.getFilledInLabel);
        String userAddLabel = labelName.getText().toString().trim();

        user = (User) getIntent().getSerializableExtra("User");
        userId = user.getId();

        if (userAddLabel.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please fill in a label", Toast.LENGTH_SHORT).show();
        }
        if (userAddLabel.matches(".*\\d.*")) {
            Toast.makeText(getApplicationContext(), "Please only use letters", Toast.LENGTH_SHORT).show();
        } else {
            Label label = new Label(userId, userAddLabel);
            UserDatabase.getExecutor().execute(() -> {
                UserDatabase.getDatabase(getApplicationContext()).labelDao().addLabel(label);
                runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), "Label is added", Toast.LENGTH_SHORT).show();
                });
            });
            finish();
            startActivity(getIntent());
        }
    }

    public void deleteLabel(Label label) {
        UserDatabase.getDatabase(getApplicationContext()).labelDao().deleteLabel(label);
    }
}