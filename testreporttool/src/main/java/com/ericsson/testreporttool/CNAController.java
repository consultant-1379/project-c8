package com.ericsson.testreporttool;

import com.ericsson.testreporttool.database.*;
import com.ericsson.testreporttool.jenkinsfunctions.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
public class CNAController {

    @Autowired
    ProjectRepository repository;

    @Autowired
    List<TestData> testData;

    @Autowired
    Map<String, Projects> products;

    private static final String TEST_OBJECT = "testObject";
    private static final String TEST_DATA = "testData";
    private static final String CNA_TITLE = "CNA - ";


    @RequestMapping("/cna/")
    public String cnaList(Model model) {
        List<String> cnaNames = ProductService.getAllCNA(repository).stream().map(product -> product.getName()).collect(Collectors.toList());
        model.addAttribute("cnas", cnaNames);
        return "cnaList";
    }

    @RequestMapping("/cna/reports/")
    public String cnaReports(Model model, @RequestParam("cna") String cna) {
        model.addAttribute("options", "cna=" + cna);
        model.addAttribute("title", CNA_TITLE + cna);
        return "reports";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/cna/reports/test-period")
    public String cnaTestPeriod(Model model,
                                @RequestParam("cna") String cna,
                                @RequestParam("start") String start,
                                @RequestParam("end") String end)
    {
        Date startDate = new Date(Long.parseLong(start));
        Date endDate = new Date(Long.parseLong(end));

        List<Object> data = ProductService.getListOfJenkinsFromPeriodCNA(repository, startDate, endDate, cna);



        List<JenkinsData> mainData = new ArrayList<>();
        List<JenkinsData> jenkinsData;


        for (int i = 0; i < data.size(); i++) {
            jenkinsData = (List<JenkinsData>) data.get(i);
            if(jenkinsData.isEmpty()) continue;
            else {
                for(JenkinsData j : jenkinsData) {
                    mainData.add(j);
                }
            }
        }

        model.addAttribute(TEST_OBJECT, CNA_TITLE + cna);

        if(mainData.isEmpty()) return "noResults";
        model.addAttribute("testName", "Tests for period " + startDate + " - " + endDate);
        model.addAttribute(TEST_DATA, mainData);
        return "results";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/cna/reports/pass-fail")
    public String cnaPassFail(Model model, @RequestParam("cna") String cna) {
        model.addAttribute(TEST_OBJECT, CNA_TITLE + cna);
        model.addAttribute("testName", "Pass / Fail Rate");
        List<Object> data = ProductService.getNumberOfTestsForCNA(repository, cna);



        List<JenkinsData> mainData = new ArrayList<>();
        List<JenkinsData> jenkinsData;


        for (int i = 0; i < data.size(); i++) {
            jenkinsData = (List<JenkinsData>) data.get(i);
            if(jenkinsData.isEmpty()) continue;
            else {
                for(JenkinsData j : jenkinsData) {
                    mainData.add(j);
                }
            }
        }

        if(mainData.isEmpty()) return "noResults";

        model.addAttribute(TEST_DATA, mainData);
        return "results";
    }

    @RequestMapping("/cna/reports/highest-fail")
    public String cnaHighestFail(Model model, @RequestParam("cna") String cna) {
        model.addAttribute(TEST_OBJECT, CNA_TITLE + cna);
        model.addAttribute("testName", "Highest Failure Rate");
        JenkinsData data = ProductService.getHighestFailRateCNA(repository, cna);
        if(data == null) return "noResults";
        else {
            model.addAttribute(TEST_DATA, data);
            return "results";
        }
    }
}