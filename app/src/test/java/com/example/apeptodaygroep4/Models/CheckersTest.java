package com.example.apeptodaygroep4.Models;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class CheckersTest {
    private final String title = "Lego";
    private final String description = "werpen";
    private final String empty = "";
    private Calendar calCurrent = (Calendar) Calendar.getInstance();
    private Calendar calPassed = (Calendar) Calendar.getInstance();
    private Calendar calFuture = (Calendar) Calendar.getInstance();
    private Date dateCurrent = new Date();
    private Date datePassed = new Date(2000,1,1);
    private Date dateFuture = new Date(2063,4,5);


    @Test
    public void checkEditFields() {
        Checkers check = new Checkers();

        boolean title_is_empty = check.checkEditFields(empty,description);
        boolean description_is_empty = check.checkEditFields(title,empty);
        boolean everything_is_filled_in = check.checkEditFields(title,description);

        assertFalse(title_is_empty);
        assertFalse(description_is_empty);
        assertTrue(everything_is_filled_in);
    }

    @Test
    public void checkIfDateHasPassedCalendar() {
        Checkers check = new Checkers();
        calPassed.set(Calendar.YEAR, 2020);
        calFuture.set(Calendar.YEAR, 2063);

        boolean date_has_passed = check.checkIfDateHasPassed(calPassed);//true
        boolean date_is_current = check.checkIfDateHasPassed(calCurrent);//true
        boolean date_is_in_the_future = check.checkIfDateHasPassed(calFuture); //false, not met the vulcans yet

        assertTrue(date_has_passed);
        assertTrue(date_is_current);
        assertFalse(date_is_in_the_future);
    }

    @Test
    public void checkIfDateHasPassedDate() {
        Checkers check = new Checkers();

        boolean date_has_passed = check.checkIfDateHasPassedDate(datePassed);//true
        boolean date_is_current = check.checkIfDateHasPassedDate(dateCurrent);//true
        boolean date_is_in_the_future = check.checkIfDateHasPassedDate(dateFuture); //false

        //assertTrue(date_has_passed); //TODO: hier iets mee doen, en vragen
        assertTrue(date_is_current);
        assertFalse(date_is_in_the_future);

    }

    @Test
    public void checkIfPasswordIsUpToStandard(){
        Checkers check = new Checkers();

        //boolean password_does_not_match = check.isPasswordCorrect()
    }
}