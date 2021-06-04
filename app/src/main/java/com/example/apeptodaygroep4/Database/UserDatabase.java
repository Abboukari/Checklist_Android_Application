package com.example.apeptodaygroep4.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Models.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

}
