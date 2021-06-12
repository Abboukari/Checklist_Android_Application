package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.DoneTask;
import java.util.List;

@Dao
public interface DoneTaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTaskDone(DoneTask task);

    @Query("SELECT Title, uIdLabel FROM Task WHERE uIdUser = :userId")
    List<DoneTask> getTasksDone(int userId);

}
