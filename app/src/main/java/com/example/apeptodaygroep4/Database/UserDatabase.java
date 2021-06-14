package com.example.apeptodaygroep4.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import androidx.room.TypeConverters;

import com.example.apeptodaygroep4.Dao.DoneTaskDao;
import com.example.apeptodaygroep4.Dao.TaskDao;
import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Models.Converters;
import com.example.apeptodaygroep4.Models.DoneTask;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Task.class, DoneTask.class}, version = 1, exportSchema = false)

@TypeConverters(Converters.class)

public abstract class UserDatabase extends RoomDatabase {
    private static final int numberOfThreads = 4;
    private static final ExecutorService dbExecutor = Executors.newFixedThreadPool(numberOfThreads);

    public abstract UserDao getUserDao();
    public abstract TaskDao taskDao();
    public abstract DoneTaskDao doneTaskDao();

    public static ExecutorService getExecutor(){
        return dbExecutor;
    }

    public static final UserDatabase getDatabase(final Context context){
        UserDatabase userDatabase;

        synchronized (UserDatabase.class){
            userDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class,
                    "ToDayDatabase")
                    .build();
        }
        return userDatabase;
    }

}
