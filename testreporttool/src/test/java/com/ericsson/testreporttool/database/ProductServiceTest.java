package com.ericsson.testreporttool.database;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
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
class ProductServiceTest {

    @Autowired
    ProjectRepository repository;

    @Test
    void getAllCNA() {
        List<Product> res = ProductService.getAllCNA(repository);
        assertFalse(res.isEmpty());
    }

    @Test
    void getAllCXP() {
        List<Projects> res = ProductService.getAllCXP(repository);
        assertFalse(res.isEmpty());
    }

    @Test
    void getAllTestsForENM() {
        List<JenkinsData> result = ProductService.getAllTestsForENM(repository);
        assertNotNull(result);
    }

    @Test
    void getNumberOfTestsForProduct() {
        int expected = 3;
        List<JenkinsData> result = ProductService.getNumberOfTestsForProduct(repository,"CXP 123");
        assertEquals(expected,result.size());
    }

    @Test
    void getNumberOfTestsForCNA() {
        int expected = 1;
        List<Object> data = ProductService.getNumberOfTestsForCNA(repository,"CNA1");
        assertEquals(expected,data.size());
    }

    @Test
    void getUrl() {
        String expected = "https://gerrit.ericsson.se/#/admin/projects/OSS/com.ericsson.product.repo.1";
        String productNum = "CXP 123";
        String result = ProductService.getUrl(repository,productNum);
        assertEquals(expected,result);
    }

    @Test
    void getHighestTestFailureENM1() {
        JenkinsData data = ProductService.getHighestTestFailureENM(repository);
        assertEquals(5,data.getFail());
    }

    @Test
    void getHighestTestFailureAProduct() {
        JenkinsData data = ProductService.getHighestTestFailureAProduct(repository,"CXP 123");
        System.out.println(data.getDate());
        assertEquals(5,data.getFail());
    }

    @Test
    void getHighestTestFailureCNA() {
        JenkinsData data = ProductService.getHighestTestFailureCNA(repository,"CNA1");
        assertEquals(5,data.getFail());
    }

    @Test
    void getListOfJenkinsFromPeriodENM() {
    }

    @Test
    void getListOfJenkinsFromPeriodAProduct() {
    }

    @Test
    void getListOfJenkinsFromPeriodCNA() {
    }


    @Test
    void getProductNumberFromJenkinsJob() {
        String expected = "CXP 123";
        String jenkinsJob = "com.ericsson.product.repo.1";
        String result = ProductService.getProductNumberFromJenkinsJob(repository,jenkinsJob);
        assertEquals(expected,result);
    }

    @Test
    void getHighestFailRateCNA() {
        JenkinsData data = ProductService.getHighestFailRateCNA(repository,"CNA1");
        double expected = 50.0;
        assertEquals(expected,data.getFailRate());
    }

    @Test
    void getHighestFailRateENM() {
        JenkinsData data = ProductService.getHighestFailRateENM(repository);
        double largestFail = data.getFailRate();
        double expected = 50.0;
        assertEquals(expected,largestFail);
    }

    @Test
    void getHighestFailRateProduct() {
        JenkinsData data = ProductService.getHighestFailRateProduct(repository,"CXP 123");
        double expected = 50.0;
        assertEquals(expected,data.getFailRate());
    }
}