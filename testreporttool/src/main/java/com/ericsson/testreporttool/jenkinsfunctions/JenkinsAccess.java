package com.ericsson.testreporttool.jenkinsfunctions;


import com.ericsson.testreporttool.secrets.secrets;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class JenkinsAccess {

    //Create Server Link
    private JenkinsServer server;
    //Will story all jobs to be queried
    private Map<String, Job> jobs;
    //Max amount we take from jenkins
    private static final int MAX_DATA = 100;
    //Creates the Accesspoint
    public JenkinsAccess() {
        try {
            server = new JenkinsServer(new URI("https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/"), secrets.USERNAME,secrets.PASSWORD);
            jobs = server.getJobs();
        } catch (IOException e) {

        }
        catch (URISyntaxException e) {

        }
    }

    //Returns map of all job URLS
    public List<String> getJobUrls()
    {
        ArrayList<String> urls = new ArrayList<>();
        for(Job p: jobs.values())
        {
            urls.add(p.getUrl());
        }

        //get subset
        return urls.subList(MAX_DATA,urls.size());
    }

    //Returns the array of Test Results
    public ArrayList<TestData> getTestResults()
    {
           ArrayList<TestData> testData = new ArrayList<>();
        int i = 0;

        for(Job p: jobs.values()) {
            //System.out.println(i);
            BuildWithDetails build = null;
            TestResult result = null;
            try {
                build = p.details().getLastCompletedBuild().details();
                result = build.getTestResult();
            } catch (IOException e) {
            }
            catch (NullPointerException e) {
            }

            TestData newData;

            if(build == null)
            {
                newData = new TestData(p.getUrl(),0);
                testData.add(newData);
            }
            else if (result != null) {
                newData = new TestData(p.getUrl(), result.getPassCount(), result.getFailCount(), build.getTimestamp());
                testData.add(newData);
            }
            else
            {
                newData = new TestData(p.getUrl(),build.getTimestamp());
                testData.add(newData);
            }
            /*if(i == MAX_DATA)
            {
                break;
            }*/
            i++;
        }
        return testData;
    }

    //Gets basic info for number of jobs
    public int getNumOfJobs()
    {
        return jobs.size();
    }
}
