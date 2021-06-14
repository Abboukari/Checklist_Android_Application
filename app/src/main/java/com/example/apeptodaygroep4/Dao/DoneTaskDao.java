package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.DoneTask;

import java.util.List;

@Dao
public interface DoneTaskDao {

    @Insert
    void addTaskDone(DoneTask task);

    @Query("SELECT * FROM doneTask WHERE doneUserIdUser = :userId")
    List<DoneTask> getTitleTasksDone(int userId);

    @Delete
    void deleteDoneTask(DoneTask doneTask);
}
