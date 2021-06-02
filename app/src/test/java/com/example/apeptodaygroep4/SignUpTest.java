package com.example.apeptodaygroep4;

import android.widget.Toast;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpTest extends TestCase { //extends jUnit3 -> hoe om te zetten naar 4 of 5?
    private final String NAME = "Renee";
    private final String PASS = "Pannekoek";
    private final String DIFFERENT_PASS = "LeffeBlond";
    private final String EMPTY = "";

    @Test
    public void signUpBtn() {

        //ARRANGE
        SignUp signUp = new SignUp();

        //ACT
        boolean name_is_empty = checkIfFieldsCorrect(EMPTY,PASS,PASS);
        boolean pass1_is_empty = checkIfFieldsCorrect(NAME,EMPTY,PASS);
        boolean pass2_is_empty = checkIfFieldsCorrect(NAME,PASS,EMPTY);
        boolean all_is_empty = checkIfFieldsCorrect(EMPTY,EMPTY,EMPTY);
        boolean pass1_not_equal_to_pass2 = checkIfFieldsCorrect(NAME,PASS,DIFFERENT_PASS);
        boolean name_is_not_empty_pass1_equals_pass2 = checkIfFieldsCorrect(NAME,PASS,PASS);

        //ASSERT
        assertFalse(name_is_empty);
    }

    @Test
    public boolean checkIfFieldsCorrect(String name, String pass1, String pass2){

        boolean fields;

        if (name.equals("") || pass1.equals("") || pass2.equals("")){
            fields = false;
            //Toast.makeText(getApplicationContext(),"No field shall be left unwritten", Toast.LENGTH_LONG).show();
        }else if (!pass1.equals(pass2)){
            fields = false;
           // Toast.makeText(getApplicationContext(),"just like my socks I need matching passwords", Toast.LENGTH_LONG).show();
        } else {
            fields = true;
        }
        return fields;
    }
}