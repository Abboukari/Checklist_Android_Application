package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task WHERE uIdUser = :userId ") //AND completed = false
    List<Task> getTaskList(int userId);

    @Query("SELECT Title, uIdUser FROM Task WHERE uIdUser = :userId")
    List<Task> getTilteTasks(int userId);


    @Insert
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);
}
