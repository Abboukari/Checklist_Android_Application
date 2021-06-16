package com.example.apeptodaygroep4.UserActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apeptodaygroep4.Database.UserDatabase;
import com.example.apeptodaygroep4.Models.Label;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;
import com.example.apeptodaygroep4.R;

public class UpdateLabel extends AppCompatActivity {

    private Label label = new Label();
    private User user;
    private Task task;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_label);

        task = (Task) getIntent().getSerializableExtra("Task");
        label = (Label) getIntent().getSerializableExtra("Label");
        user = (User) getIntent().getSerializableExtra("User");

        String selectedLabel = label.getLabelName();
        TextView showSelectedLabel = findViewById(R.id.showLabelToUpdate);

        showSelectedLabel.setText(selectedLabel);

    }

    public void updateLabelInDatabase(View view){

        EditText editTextLabel = findViewById(R.id.editTextNewLabelName);
        String changeLabel = editTextLabel.getText().toString().trim();

        if (changeLabel.isEmpty()){
            Toast.makeText(this, "Please fill in the new label name", Toast.LENGTH_SHORT).show();
        } else {

            UserDatabase.getExecutor().execute(()->{

                user = (User) getIntent().getSerializableExtra("User");
                task = (Task) getIntent().getSerializableExtra("Task");
                label = (Label) getIntent().getSerializableExtra("Label");
                userId = user.getId();

                label.updateLabel(label.getLabelId(), userId, changeLabel);

                UserDatabase.getDatabase(getApplicationContext()).labelDao().updateLabel(label);
                runOnUiThread(()->{

                    Toast.makeText(getApplicationContext(), "Label has been updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,AddLabel.class);
                    intent.putExtra("User",user);
                    intent.putExtra("Task", task);
                    intent.putExtra("userId",userId);
                    intent.putExtra("Label",label);
                    startActivity(intent);
                });
            });
        }
    }
}