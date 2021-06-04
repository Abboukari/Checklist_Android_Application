package com.example.apeptodaygroep4.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Models.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1)
public abstract class DatabaseUser extends RoomDatabase {
    private static final int numberOfThreads = 4;
    private static final ExecutorService dbExecutor = Executors.newFixedThreadPool(numberOfThreads);
    public abstract UserDao userDao();

    public static ExecutorService getDbExecutor() { return dbExecutor;}
    public static final DatabaseUser getDatabaseUser (final Context context){
        DatabaseUser databaseUser;

        synchronized (DatabaseUser.class){
            databaseUser = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DatabaseUser.class,
                    "Database_of_Users")
                    .build();
        }
        return databaseUser;
    }
}
