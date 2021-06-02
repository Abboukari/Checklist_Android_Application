package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.User;

import java.util.List;

@Dao
public interface UserDao {

    //SELECT
   // @Query("SELECT * FROM user")

    //INSERT
    @Insert
    void addUser(User user);
}
