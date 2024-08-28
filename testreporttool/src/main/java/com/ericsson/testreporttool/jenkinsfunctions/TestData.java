package com.ericsson.testreporttool.jenkinsfunctions;

import java.util.Date;

public class TestData {

    private String url;
    private int testsPassed;
    private int testsFailed;
    private Date DateBuilt;

    public TestData(String url, int testsPassed, int testsFailed, long dateBuilt) {
        this.url = url;
        this.testsPassed = testsPassed;
        this.testsFailed = testsFailed;
        DateBuilt = new Date(dateBuilt);
    }

    public TestData(String url,long dateBuilt) {
        this.url = url;
        this.testsPassed = 0;
        this.testsFailed = 0;
        DateBuilt = new Date(dateBuilt);
    }

    public TestData() {
        this.url = "";
        this.testsPassed = 0;
        this.testsFailed = 0;
        DateBuilt = new Date(0);
    }



    public void setUrl(String url) {
        this.url = url;
    }

    public void setTestsPassed(int testsPassed) {
        this.testsPassed = testsPassed;
    }

    public void setTestsFailed(int testsFailed) {
        this.testsFailed = testsFailed;
    }

    public void setDateBuilt(long dateBuilt) {
        DateBuilt = new Date(dateBuilt);
    }

    public String getUrl() {
        return url;
    }

    public int getTestsPassed() {
        return testsPassed;
    }

    public int getTestsFailed() {
        return testsFailed;
    }

    public Date getDateBuilt() {
        return DateBuilt;
    }


    @Override
    public String toString() {
        return "TestData{" +
                "url='" + url + '\'' +
                ", testsPassed=" + testsPassed +
                ", testsFailed=" + testsFailed +
                ", DateBuilt=" + DateBuilt +
                '}';
    }
}
