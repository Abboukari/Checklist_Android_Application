package com.example.apeptodaygroep4.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.apeptodaygroep4.Dao.LabelDao;
import com.example.apeptodaygroep4.Dao.TaskDao;
import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Models.Converters;
import com.example.apeptodaygroep4.Models.LabelDb;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;

@Database(entities = {User.class, Task.class, LabelDb.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)

public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    //TODO: TaskDao and LAbelDao
    public abstract TaskDao getTaskDao();



}
