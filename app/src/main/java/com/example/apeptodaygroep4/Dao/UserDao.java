package com.example.apeptodaygroep4.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apeptodaygroep4.Models.User;

import java.util.List;

@Dao
public interface UserDao {

    //SELECT
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    /*@Query(("SELECT uId FROM user"))
    List<User> getUidFromUser(); */
    //moet ie meteen gerbuikt worden?



    //INSERT
    @Insert
    void addUser(User user);

    /*//UPDATE
    @Update
    void updateUser(User user);*/
}
