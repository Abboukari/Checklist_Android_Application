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

    @Query("SELECT doneTitle, doneUserIdUser FROM doneTask WHERE doneUserIdUser = :userId")
    List<DoneTask> getTitleTasksDone(int userId);

    @Query("DELETE FROM DoneTask")
    void deleteAllData();

}
