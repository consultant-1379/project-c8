package com.ericsson.testreporttool;

import com.ericsson.testreporttool.database.JenkinsData;
import com.ericsson.testreporttool.database.ProductService;
import com.ericsson.testreporttool.database.ProjectRepository;
import com.ericsson.testreporttool.database.Projects;
import com.ericsson.testreporttool.jenkinsfunctions.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Map;


@Controller
public class ENMController {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    List<TestData> testData;

    @Autowired
    Map<String, Projects> products;

    private static final String TEST_OBJECT = "testObject";
    private static final String TEST_DATA = "testData";
    
    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("products", products);
        return "home";
    }

    @RequestMapping("/enm/")
    public String enmReports(Model model) {
        model.addAttribute("options", "product=enm");
        model.addAttribute("title", "ENM (all products)");
        return "reports";
    }

    @RequestMapping("/enm/test-period")
    public String enmTestPeriod(Model model,
                                @RequestParam("start") String start,
                                @RequestParam("end") String end) {
        Date startDate = new Date(Long.parseLong(start));
        Date endDate = new Date(Long.parseLong(end));

        List<JenkinsData> jenkinsData = ProductService.getListOfJenkinsFromPeriodENM(repository, startDate, endDate);
        model.addAttribute(TEST_OBJECT, "ENM (all products)");
        model.addAttribute("testName", "Tests for period " + startDate + " - " + endDate);
        model.addAttribute(TEST_DATA, jenkinsData);
        return "results";
    }

    @RequestMapping("/enm/pass-fail")
    public String enmPassFail(Model model) {
        List<JenkinsData> jenkinsData = ProductService.getAllTestsForENM(repository);
        model.addAttribute(TEST_OBJECT, "ENM (all products)");
        model.addAttribute("testName", "Pass / Fail Rate");
        model.addAttribute(TEST_DATA, jenkinsData);
        return "results";
    }

    @RequestMapping("/enm/highest-fail")
    public String enmHighestFail(Model model) {
        JenkinsData jenkinsData = ProductService.getHighestFailRateENM(repository);
        model.addAttribute(TEST_OBJECT, "ENM - (All Products) ");
        model.addAttribute("testName", "Highest Failure Rate - " + jenkinsData.getNumber());
        if(jenkinsData == null) return "noResults";
        else {
            model.addAttribute(TEST_DATA, jenkinsData);
            return "singleResult";
        }
    }
}