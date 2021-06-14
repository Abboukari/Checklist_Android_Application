package com.example.apeptodaygroep4.Dao;

import android.widget.ArrayAdapter;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apeptodaygroep4.Models.DoneTask;
import com.example.apeptodaygroep4.Models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task WHERE uIdUser = :userId") //AND completed = false
    List<Task> getTaskList(int userId);

    @Query("SELECT * FROM Task WHERE uIdUser = :userId")
    List<Task> getAllDetailsFromTasks(int userId);

    @Query("SELECT uIdTask FROM Task WHERE uIdTask = :userIdTask")
    Task getTask(int userIdTask);

    @Query("SELECT uIdTask FROM Task WHERE uIdUser = :userId")
    int getTaskId(int userId);

    @Query("SELECT * FROM Task WHERE uIdTask = :userIdTask")
    Task getDetailTask(int userIdTask);

    @Insert
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM Task WHERE uIdTask = :userIdTask")
    void deleteTaskFromDatabase(int userIdTask);


    @Update
    void updateTask(Task task);
}
