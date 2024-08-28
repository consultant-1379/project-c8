package com.ericsson.testreporttool.database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JenkinsDataTest {

    JenkinsData data = new JenkinsData("CNA Test","cm/ericsson/testProject",1,2,"20/12/20");

    @Test
    void testConstructor() {
        JenkinsData newData = new JenkinsData("CNA XPT 100","cm/ericsson/anotherProject",1,2,"20/12/20");
    }

    @Test
    void getAndSetNumber() {
        String newNumber = "New Test Num";
        data.setNumber(newNumber);
        assertEquals(newNumber,data.getNumber());
    }

    @Test
    void getAndSetTesturls() {
        String newURL = "com/ericsson/testProject2";
        data.setTesturls(newURL);
        assertEquals(newURL,data.getTesturls());
    }

    @Test
    void getAndSetPass() {
        int newPass = 10;
        data.setPass(newPass);
        assertEquals(newPass,data.getPass());
    }


    @Test
    void getAndSetFail() {
        int newFail = 2;
        data.setFail(newFail);
        assertEquals(newFail,data.getFail());
    }


    @Test
    void getAndSetDate() {
        String date = "7/7/2007";
        data.setDate(date);
        assertEquals(date,data.getDate());
    }

    @Test
    void getAndSetPassRate() {
        double newPassRate = 0.5;
        data.setPassRate(newPassRate);
        assertEquals(newPassRate,data.getPassRate());
    }

}