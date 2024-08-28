package com.ericsson.testreporttool.jenkinsfunctions;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestDataTest {

    TestData data = new TestData();

    @Test
    void testFullConstructor() {
        TestData tempData = new TestData("job/ericsson/test",1,2,2);
        String url = "job/ericsson/test";
        assertEquals(url,tempData.getUrl());
    }

    @Test
    void testHalfConstructor() {
        TestData tempData = new TestData("job/test",0);
        String url = "job/test";
        assertEquals(url,tempData.getUrl());
    }

    @Test
    void testEmptyConstructor() {
        TestData tempData= new TestData();
        assertEquals("",tempData.getUrl());
    }

    @Test
    void setAndGetUrl() {
        String newUrl = "job/test/home";
        data.setUrl(newUrl);
        assertEquals(newUrl,data.getUrl());
    }

    @Test
    void setAndGetTestsPassed() {
        int newPassed = 10;
        data.setTestsPassed(newPassed);
        assertEquals(newPassed,data.getTestsPassed());
    }

    @Test
    void setAndGetTestsFailed() {
        int newFail = 10;
        data.setTestsFailed(newFail);
        assertEquals(newFail,data.getTestsFailed());
    }

}