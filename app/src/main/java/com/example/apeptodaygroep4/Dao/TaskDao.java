package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apeptodaygroep4.Models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task WHERE uIdUser = :userId AND done = 0")//0 == false
    List<Task> getAllDetailsFromTasks(int userId);

    @Query("SELECT * FROM Task WHERE uIdUser = :userId AND done = 1")//1 == true
    List<Task> getAllDoneTasks(int userId);

    @Insert
    void addTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update()
    void updateTask(Task task);
}
