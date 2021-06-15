package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.Label;

import java.util.List;

@Dao
public interface LabelDao {

    @Query("SELECT * FROM Label WHERE userId = :userId")
    List<Label> getAllLabels(int userId);

    @Insert
    void addLabel (Label label);

}
