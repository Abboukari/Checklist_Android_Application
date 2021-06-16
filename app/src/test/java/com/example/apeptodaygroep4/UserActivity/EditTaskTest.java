package com.example.apeptodaygroep4.UserActivity;

import android.widget.EditText;

import com.example.apeptodaygroep4.Models.User;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class EditTaskTest {

    private final String userName = "Karel";
    private final String password = "Karmeliet";
    private final String email = "brutus@myLittlePony.nl";
    private final String title = "Zwemmen";
    private final String description = "plons;";
    private final Date date = new Date();
    private final Date emptyDate = null;
    private final String empty = "";
    private final User user = new User(userName,password,email);
    private final Integer userId = user.getId();

    @Test
    public void checkEditFieldsTest() {
        EditTask editTask = new EditTask();

        boolean all_is_filled = editTask.checkEditFields(title,description,date);
        boolean title_is_empty = editTask.checkEditFields(empty,description,date);


        assertTrue(all_is_filled);
        assertFalse(title_is_empty);
    }
}