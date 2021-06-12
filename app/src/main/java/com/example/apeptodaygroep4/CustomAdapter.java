package com.example.apeptodaygroep4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apeptodaygroep4.Models.Task;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Task> {
    private static final String TAG = "taskListAdapter";
    private Context mContext;
    private int mResource;
    private ArrayList<Task> mTasks;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Task> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mTasks = objects;
    }

    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Nullable
    @Override
    public Task getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get info
        String title = getItem(position).getTitle();
        String label = getItem(position).getlabelName();
        Boolean checked = getItem(position).isCompleted();

        //Create Task object with info
        Task task = new Task(title,label,checked);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false); //Viewholder pattern

        TextView tvTitle = (TextView) convertView.findViewById(R.id.text_view_name);
        TextView tvLabel = (TextView) convertView.findViewById(R.id.label);
        CheckBox cbChecked = (CheckBox) convertView.findViewById(R.id.check_box_completed);//TODO: checkbox een int???

        tvTitle.setText(title);
        tvLabel.setText(label);
        cbChecked.setChecked(checked);

        return convertView;
    }
}
