package com.ericsson.testreporttool.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectsTest {

    Projects project = new Projects("CSA 111","CRA 112","CNA 113","CXP 114","PRA 111","com.ericssoon.repo","TAF 112");
    @Test
    void testConstructor()
    {
        Projects temp = new Projects("CSA 111","CRA 112","CNA 113","CXP 114","PRA 111","com.ericssoon.repo","TAF 112");
        assertEquals("CSA 111",temp.getCSA());
    }

    @Test
    void getAndSetCSA() {
        String newCSA = "CSA 222";
        project.setCSA(newCSA);
        assertEquals(newCSA,project.getCSA());
    }

    @Test
    void getAndSetCRA() {
        String newCRA = "CRA 222";
        project.setCRA(newCRA);
        assertEquals(newCRA,project.getCRA());
    }

    @Test
    void getAndSetCNA() {
        String newCNA = "CNA 222";
        project.setCNA(newCNA);
        assertEquals(newCNA,project.getCNA());
    }

    @Test
    void getAndSetCXP() {
        String newCXP = "CXP 222";
        project.setCXP(newCXP);
        assertEquals(newCXP,project.getCXP());
    }

    @Test
    void getAndSetNumber() {
        String newCRA = "PRA 222";
        project.setNumber(newCRA);
        assertEquals(newCRA,project.getNumber());
    }

    @Test
    void getAndSetRPM() {
        String newRPM = "com.ericsson.repo";
        project.setRPM(newRPM);
        assertEquals(newRPM,project.getRPM());
    }

    @Test
    void getAndSetTAF() {
        String newTAF = "TAF 222";
        project.setTAF(newTAF);
        assertEquals(newTAF,project.getTAF());
    }

}