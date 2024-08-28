package com.ericsson.testreporttool;

import com.ericsson.testreporttool.database.ProductService;
import com.ericsson.testreporttool.database.ProjectRepository;
import com.ericsson.testreporttool.database.Projects;
import com.ericsson.testreporttool.jenkinsfunctions.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.ericsson.testreporttool.database.JenkinsData;

import java.sql.Date;
import java.util.List;
import java.util.Map;


@Controller
public class ProductController {

    @Autowired
    private ProjectRepository repository;

    @Autowired
    List<TestData> testData;

    @Autowired
    Map<String, Projects> products;

    @RequestMapping("/product/reports/")
    public String productReports(Model model, @RequestParam("product") String product) {
        String title = products.get(product).getCXP();
        model.addAttribute("options", "product=" + product);
        model.addAttribute("title", title);
        return "reports";
    }

    @RequestMapping("/product/reports/test-period")
    public String productTestPeriod(
            Model model,
            @RequestParam("product") String product,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        Projects p = products.get(product);

        Date startDate = new Date(Long.parseLong(start));
        Date endDate = new Date(Long.parseLong(end));

        List<JenkinsData> testData = ProductService.getListOfJenkinsFromPeriodAProduct(repository, startDate, endDate, product);

        System.out.println("Date results: " + testData);

        model.addAttribute("testObject", p.getCXP());
        model.addAttribute("testName", "Tests for period " + startDate + " - " + endDate);

        if(testData.isEmpty()) {
            return "noResults";
        }
        else{
            model.addAttribute("testData", testData);
            return "results";
        }

    }

    @RequestMapping("/product/reports/pass-fail")
    public String getPassFailRate(Model model, @RequestParam("product") String product) {
        Projects p = products.get(product);

        List<JenkinsData> testData = ProductService.getNumberOfTestsForProduct(repository, product);

        model.addAttribute("testObject", p.getCXP());
        model.addAttribute("testName", "Pass / Fail Rate");

        if(testData.isEmpty()) {
            return "noResults";
        }
        else{
            model.addAttribute("testData", testData);
            return "results";
        }
    }

    @RequestMapping("/product/reports/highest-fail")
    public String getHighestFailRate(Model model, @RequestParam("product") String product) {

        JenkinsData jenkinsData = ProductService.getHighestFailRateProduct(repository, product);
        Projects p = products.get(product);
        model.addAttribute("testObject", p.getCXP());
        model.addAttribute("testName", "Highest Failure Rate");
        if(jenkinsData == null || jenkinsData.getFail() == 0){
            return "noResults";
        }
        else {
            model.addAttribute("testData", jenkinsData);
            return "singleResult";
        }
    }
}
