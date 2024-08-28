package com.ericsson.testreporttool;

import com.ericsson.testreporttool.database.ProductService;
import com.ericsson.testreporttool.database.ProjectRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@JdbcTest
@Import({ProjectRepository.class})
//@ContextConfiguration(classes = {ENMController.class})
@AutoConfigureTestDatabase
@Sql({"classpath:schema.sql","classpath:sample-data.sql"})
class ENMControllerTest {

    @Mock
    private Model model;

    private boolean mockInitialized = false;

    @Autowired
    ProjectRepository repository;

    @Autowired
    JdbcTemplate template;

    @Before
    public void setUp() {
        //initialize mockito
        if (!mockInitialized) {
            MockitoAnnotations.initMocks(this);
            mockInitialized = true;
        }

    }

    @Test
    void home(){
        // enm controller instance
        ENMController enmController = new ENMController();

        String returnValue = enmController.home(model);
        verify(model, times(1)).addAttribute("products", null);
        assertEquals("home", returnValue);
    }

    @Test
    void enmReports() {
        // enm controller instance
        ENMController enmController = new ENMController();

        String returnValue = enmController.enmReports(model);
        verify(model, times(1)).addAttribute("options", "product=enm");
        verify(model, times(1)).addAttribute("title", "ENM (all products)");
        assertEquals("reports", returnValue);
    }

    @Test
    void enmTestPeriod() {
    }

    @Test
    void enmPassFail() {
        // enm controller instance
//        ENMController enmController = context.getBean(ENMController.class);
//        ENMController enmController = new ENMController();
//
//        String returnValue = enmController.enmPassFail(model);
//        verify(model, times(1)).addAttribute("testObject", "ENM (all products)");
//        verify(model, times(1)).addAttribute("testName", "Pass / Fail Rate");
//        verify(model, times(1)).addAttribute("testData", ProductService.getAllTestsForENM(repository));
//        assertEquals("results", returnValue);
    }

    @Test
    void enmHighestFail() {
        // enm controller instance
//        ENMController enmController = new ENMController();
//        System.out.println(ProductService.getHighestTestFailureENM(repository));
//
//        String returnValue = enmController.enmHighestFail(model);
//        verify(model, times(1)).addAttribute("testObject", "ENM - (All Products) ");
//        verify(model, times(1)).addAttribute("testName", "Highest Failure Rate - " + 0);
//        verify(model, times(1)).addAttribute("jenkinsData", null);
//        assertEquals("singleResult", returnValue);
    }
}