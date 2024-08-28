package com.ericsson.testreporttool.database.Processor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessInfoTest {

    @Test
    void processJobURLPCRendings() {
        String jenkinsURl = "https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/transport-api_PCR/";
        String expectedResult = "transport-api";

        assertEquals(expectedResult,ProcessInfo.ProcessJobURL(jenkinsURl));

    }

    @Test
    void processJobURLReleaseendings() {
        String jenkinsURl = "https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/tls-transport-library_Release/";
        String expectedResult = "tls-transport-library";

        assertEquals(expectedResult,ProcessInfo.ProcessJobURL(jenkinsURl));

    }

    @Test
    void processNoSpecifiedEndings() {
        String jenkinsURl = "https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/tls-transport-library/";
        String expectedResult = "tls-transport-library/";

        assertEquals(expectedResult,ProcessInfo.ProcessJobURL(jenkinsURl));

    }
}