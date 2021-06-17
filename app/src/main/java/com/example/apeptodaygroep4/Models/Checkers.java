package com.example.apeptodaygroep4.Models;


import android.content.Context;
import android.widget.Toast;

import com.example.apeptodaygroep4.RegisterActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Checkers {
   /* private String title;
    private String description;
    private Calendar calendar;*/


    public Checkers(){
    }

    public boolean checkEditFields(String title, String description) {
        boolean status;

        if (title.isEmpty() || description.isEmpty()) {
            status = false;
        } else {
            status = true;
        }
        return status;
    }

    public boolean checkIfDateHasPassed(Calendar cal) {
        boolean hasPassed = true;
        Calendar current = new GregorianCalendar();
        current.getTime();

        if (cal.compareTo(current) > 0) {
            hasPassed = false;
            //gekozen datum is NA de huidige datum
        }
        return hasPassed;
    }

    public boolean checkIfDateHasPassedDate(Date date){
        boolean dateHasPassed = true;
        Date currentDate = new Date();

        if (date.compareTo(currentDate) > 0){
            dateHasPassed = false;
        }
        return dateHasPassed;
    }

    public boolean isPasswordCorrect(String password1, String password2, Context context){
        int lengthUserPassword = 6;

        if (!password1.equals(password2)){
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show();
            //Toast.makeText(getApplicationContext(), "Password is not matching", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password1.length() <= lengthUserPassword){
            Toast.makeText(context, "You need a longer password!", Toast.LENGTH_SHORT).show();
           Toast.makeText(context, "At least 6 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
