package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.Task;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task WHERE uIdUser = :userId ") //AND completed = false
    Task getTaskList(int userId);

    @Insert
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);
}
