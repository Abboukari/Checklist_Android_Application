package com.example.apeptodaygroep4;

import com.example.apeptodaygroep4.Models.Checkers;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterActivityTest {
    final String PASSWORD1 = "hallo123";
    final String PASSWORD2 = "hallo1234";
    final String PASSWORD3 = "hallo";
    final String PASSWORD4 = "hall";

    final Checkers check = new Checkers();

    @Test
    public void isPasswordCorrect_with_the_right_amount_of_letters_and_the_passwords_are_matching() {

        //ARRANGE
        String result = check.isPasswordCorrect(PASSWORD1,PASSWORD1);

        //ASSERTS
        assertEquals("",result);
    }

    @Test
    public void is_password_correct_method_are_not_equals_with_the_right_amount_of_letters(){

        //ARRANGE
        String result = check.isPasswordCorrect(PASSWORD1,PASSWORD2);

        //ASSERTS
        assertEquals("Password is not matching",result);
    }

    @Test
    public void is_password_correct_method_with_not_enough_letters_and_are_equal(){

        String result = check.isPasswordCorrect(PASSWORD3,PASSWORD3);

        assertEquals("You need a longer password!",result);

    }

    @Test
    public void is_password_correct_method_with_not_enough_letters_and_are_not_equal(){

        String result = check.isPasswordCorrect(PASSWORD3,PASSWORD4);

        assertEquals("Password is not matching",result);
    }

}