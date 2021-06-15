package com.example.apeptodaygroep4.UserActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.Label;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.R;
import java.util.ArrayList;

public class AddLabel extends AppCompatActivity {

    private ArrayList<Label> allUserLabels;
    private ArrayAdapter<Label> adapter;
    private User user;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);

        user = (User) getIntent().getSerializableExtra("User");
        ListView listView = findViewById(R.id.listViewLabels);

        UserDatabase.getExecutor().execute(()->{
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

            runOnUiThread(()->{
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

        if (item.getItemId() == R.id.SelectLabelAction){

        } else if(item.getItemId() == R.id.deletedLabelAction){

        } else if (item.getItemId() == R.id.EditLabelAction){

        } else {

        }

        return super.onContextItemSelected(item);
    }

    public void addLabelToArrayList(View view){

        EditText labelName = findViewById(R.id.getFilledInLabel);
        String userAddLabel = labelName.getText().toString().trim();


        user = (User) getIntent().getSerializableExtra("User");
        userId = user.getId();

        if (userAddLabel.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please fill in a label", Toast.LENGTH_SHORT).show();
        } else {
            Label label = new Label(userId,userAddLabel);
            UserDatabase.getExecutor().execute(()->{
                UserDatabase.getDatabase(getApplicationContext()).labelDao().addLabel(label);
                runOnUiThread(()->{
                    Toast.makeText(getApplicationContext(), "Label is added", Toast.LENGTH_SHORT).show();
                });
            });
            finish();
            startActivity(getIntent());
        }
    }
}