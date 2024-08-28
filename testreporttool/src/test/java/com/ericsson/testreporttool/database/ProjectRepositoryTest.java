package com.ericsson.testreporttool.database;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({ProjectRepository.class})
@AutoConfigureTestDatabase
@Sql({"classpath:schema.sql","classpath:sample-data.sql"})
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void getAllCNA()
    {
        List<Product> res = projectRepository.getAllCna();
        assertFalse(res.isEmpty());
    }

    @Test
    void getAllCsa() {
        List<Product> res = projectRepository.getAllCsa();
        assertFalse(res.isEmpty());
    }

    @Test
    void getAllCra() {
        List<Product> res = projectRepository.getAllCra();
        assertFalse(res.isEmpty());
    }

    @Test
    void getAllCxp() {
        List<Projects> res = projectRepository.getAllCxp();
        assertFalse(res.isEmpty());
    }

    @Test
    void getUrl() {
        String expected = "https://gerrit.ericsson.se/#/admin/projects/OSS/com.ericsson.product.repo.1";
        String productNum = "CXP 123";
        String result = projectRepository.getUrl(productNum);
        assertEquals(expected,result);
    }

    @Test
    void parseJenkinsUrl() {
        String expected = "CXP 123";
        String jenkinsJob = "https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/com.ericsson.product.repo.1";
        String result = projectRepository.parseJenkinsUrl(jenkinsJob);
        assertEquals(expected,result);
    }

    @Test
    void getProductNumberFromJenkinsJob() {
        String expected = "CXP 123";
        String jenkinsJob = "com.ericsson.product.repo.1";
        String result = projectRepository.getProductNumberFromJenkinsJob(jenkinsJob);
        assertEquals(expected,result);
    }

    @Test
    void getAllTestsForENM() {
        List<JenkinsData> result = projectRepository.getAllTestsForENM();
        assertNotNull(result);
    }

    @Test
    void getNumberOfTestsForProduct() {
        List<JenkinsData> result = projectRepository.getNumberOfTestsForProduct("CXP 123");
        assertEquals(3,result.size());
    }

    @Test
    void getCxpsFromCna() {
        List<Projects> data = projectRepository.getCxpsFromCna("CNA1");
        assertTrue( data.size() == 1);
    }

    @Test
    void getAllJenkinsData() {
        List<JenkinsData> data = projectRepository.getAllJenkinsData();
        assertNotNull(data);
    }

    @Test
    void getHigestTestFailureENM() {
        List<JenkinsData> data = projectRepository.getHigestTestFailureENM();
        int lowest = data.get(data.size()-1).getFail();
        int Highest = data.get(0).getFail();
        assertTrue(Highest > lowest);
    }

    @Test
    void getHigestTestFailureForAProduct() {
        List<JenkinsData> data = projectRepository.getHigestTestFailureForAProduct("CXP 123");
        int lowest = data.get(data.size()-1).getFail();
        int Highest = data.get(0).getFail();
        assertTrue(Highest > lowest);
    }

    @Test
    void getJenkinsDataFromPeriod() {
    }

    @Test
    void getJenkinsDataFromPeriodForAProduct() {
    }
}