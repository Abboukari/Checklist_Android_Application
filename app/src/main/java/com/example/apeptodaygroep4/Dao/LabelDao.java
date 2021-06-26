package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apeptodaygroep4.Models.Label;

import java.util.List;

@Dao
public interface LabelDao {

    @Query("SELECT * FROM Label WHERE userId = :userId")
    List<Label> getAllLabels(int userId);

    @Query("SELECT * FROM Label WHERE labelId = :labelId")
    Label getLabel(int labelId);

    @Insert
    void addLabel (Label label);

    @Delete
    void deleteLabel (Label label);

    @Update()
    void updateLabel(Label label);

}
