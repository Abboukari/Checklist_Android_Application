package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.LabelDb;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LabelDao {
    @Query("SELECT * FROM LabelDb")
    List<LabelDb> getAllLabels();

    /*@Query("SELECT uIdLabel FROM LabelDb")
    ArrayList<String> getStringLabels();*/

}
