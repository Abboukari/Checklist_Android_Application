package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.apeptodaygroep4.Models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User getUser(String email, String password);

    @Query("SELECT email FROM User")
    List<String> getAllEmailFromUsers();

    @Insert
    void insert(User user);
}
