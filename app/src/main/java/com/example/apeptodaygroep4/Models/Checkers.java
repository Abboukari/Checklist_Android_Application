package com.example.apeptodaygroep4.Models;


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

    public boolean checkIfDateHasPassed(Date date){
        boolean dateHasPassed = true;
        Date currentDate = new Date();

        if (date.compareTo(currentDate) > 0){
            dateHasPassed = false;
        }
        return dateHasPassed;
    }
}
