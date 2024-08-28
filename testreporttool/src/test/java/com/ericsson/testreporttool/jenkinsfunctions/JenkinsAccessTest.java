package com.ericsson.testreporttool.jenkinsfunctions;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JenkinsAccessTest {

    JenkinsAccess access = new JenkinsAccess();

    @Test
    void getJobUrls() {
        List<String> urls = access.getJobUrls();

        assertFalse(urls.isEmpty());
    }

    @Test
    void getTestResults() {
        ArrayList<TestData> data = access.getTestResults();

        assertFalse(data.isEmpty());
    }

    @Test
    void getNumOfJobs() {
        int jobnumber = access.getNumOfJobs();
        assertNotEquals(0,jobnumber);
    }
}