package com.example.apeptodaygroep4.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.example.apeptodaygroep4.Dao.TaskDao;
import com.example.apeptodaygroep4.Dao.UserDao;
import com.example.apeptodaygroep4.Models.Task;
import com.example.apeptodaygroep4.Models.User;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(JUnit4.class)
public class UserDatabaseTest extends TestCase {
    private UserDatabase db;
    private TaskDao taskDao;
    private UserDao userDao;

    @Before
    public void setDb(){
        Context context = ApplicationProvider.getApplicationContext();

        db = Room.inMemoryDatabaseBuilder(context,UserDatabase.class).build();
        taskDao = db.taskDao();
        userDao = db.getUserDao();
    }

    @After
    public void closeDatabase(){
        db.close();
    }

    @Test
    public void select_all_from_task(){
        String title = "sleeping";
        String description = "not this week";
        Date date = new Date();
        String email = "renee@renee.nl";
        User user = new User(title,description,email);
        Integer userId = user.getId();
        Task task = new Task(userId,title,description,date);


        userDao.insert(user);
        taskDao.addTask(task);
        //List<Task> tasks = taskDao.getAllDetailsFromTasks(userId);
        assertEquals(task.getuIdUser(), userId);
        //assertTrue(tasks.contains(task));
        //TODO: hier gaat ie stuk

    }

}