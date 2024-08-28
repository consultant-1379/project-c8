package com.ericsson.testreporttool.database.Processor;

public class ProcessInfo {

    private static final String URL_BEGINNINGS = "https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/";
    private static final String PCR_SUFFIX = "_PCR/";
    private static final String RELEASE_SUFFIX = "_Release/";

    private ProcessInfo() {
        throw new IllegalStateException("ProcessInfo Class");
    }

    public static String ProcessJobURL(String JobUrl)
    {
        String removedBeginning = JobUrl.substring(URL_BEGINNINGS.length());
        if (removedBeginning.endsWith(PCR_SUFFIX)){
            return removedBeginning.substring(0, removedBeginning.length() - PCR_SUFFIX.length());
        } else if (removedBeginning.endsWith(RELEASE_SUFFIX)){
            return removedBeginning.substring(0, removedBeginning.length() - RELEASE_SUFFIX.length());
        }
        return removedBeginning;
    }
}
