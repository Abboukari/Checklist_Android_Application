package com.example.apeptodaygroep4.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Task> mList;
    private Context mContext;
    private LayoutInflater mInflater;
    private Task mTask;

    public TaskAdapter(Context context, List<Task> list){
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_task,parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder)holder;
        mTask = mList.get(position);

        myHolder.titleTask.setText(mTask.getTitle());
        myHolder.completed.setChecked(mTask.isCompleted());
        myHolder.labels.setText(mTask.getuIdLabel());//TODO: get labelName from task uIdNUmber
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        CheckBox completed;
        TextView titleTask;
        TextView labels;

        public MyHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            completed = (CheckBox)itemView.findViewById(R.id.check_box_completed);
            titleTask = (TextView)itemView.findViewById(R.id.text_view_name);
            labels = (TextView) itemView.findViewById(R.id.label_priority);
        }
    }
}
