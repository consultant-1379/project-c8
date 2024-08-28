package com.ericsson.testreporttool.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    public static List<Product> getAllCNA(ProjectRepository repository) {
        return repository.getAllCna();
    }

    public static List<Projects> getAllCXP(ProjectRepository repository) {
        return repository.getAllCxp();
    }

    public static List<JenkinsData> getAllTestsForENM(ProjectRepository repository) {
        return repository.getAllTestsForENM();
    }

    public static List<JenkinsData> getNumberOfTestsForProduct(ProjectRepository repository, String productNumber) {
        return repository.getNumberOfTestsForProduct(productNumber);
    }

    public static List<Object> getNumberOfTestsForCNA(ProjectRepository repository, String cnaName) {
        List<String> cxps = getCxpsFromCna(repository, cnaName);
        List<Object> data = new ArrayList<>();

        for (int i = 0; i < cxps.size(); i++) {
           data.add(repository.getNumberOfTestsForProduct(cxps.get(i)));

        }
        return data;
    }

    private static List<String> getCxpsFromCna(ProjectRepository repository, String cnaName) {
        List<Projects> data = repository.getCxpsFromCna(cnaName);
        List<String> cxps = new ArrayList<>();
        for(Projects projects: data) {
            cxps.add(projects.getNumber());
        }
        return cxps;
    }


    public static String getUrl(ProjectRepository repository, String productNumber) {
        return repository.getUrl(productNumber);
    }

    public static String getProductNumberFromJenkinsJob(ProjectRepository repository, String name) {
        return repository.getProductNumberFromJenkinsJob(name);
    }

    public static JenkinsData getHighestTestFailureENM(ProjectRepository repository){
        List<JenkinsData> allData = repository.getHigestTestFailureENM();
        if(allData.isEmpty()) return null;
        else return allData.get(0);
    }

    public static JenkinsData getHighestTestFailureAProduct(ProjectRepository repository, String productNumber) {
        List<JenkinsData> allData = repository.getHigestTestFailureForAProduct(productNumber);
        if(allData.isEmpty()) return null;
        else return allData.get(0);
    }

    public static JenkinsData getHighestFailRateENM(ProjectRepository repository) {
        List<JenkinsData> jenkinsData = ProductService.getAllTestsForENM(repository);
        if(jenkinsData.isEmpty()){
            return null;
        }
        else {
            jenkinsData.sort(Comparator.comparing(JenkinsData::getFailRate).reversed());
            return jenkinsData.get(0);
        }

    }

    public static JenkinsData getHighestFailRateProduct(ProjectRepository repository, String product) {
        List<JenkinsData> jenkinsData = ProductService.getNumberOfTestsForProduct(repository, product);
        if(jenkinsData.isEmpty()){
            return null;
        }
        else {
            jenkinsData.sort(Comparator.comparing(JenkinsData::getFailRate).reversed());
            return jenkinsData.get(0);
        }
    }

    public static JenkinsData getHighestFailRateCNA(ProjectRepository repository, String cnaName) {
        List<Object> data = ProductService.getNumberOfTestsForCNA(repository, cnaName);
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

        if(mainData.isEmpty()) return null;
        else {
            mainData.sort(Comparator.comparing(JenkinsData::getFailRate).reversed());
            return mainData.get(0);
        }

    }
    
    public static JenkinsData getHighestTestFailureCNA(ProjectRepository repository, String cnaName) {
        List<String> cxps = getCxpsFromCna(repository, cnaName);

        List<Object> data = new ArrayList<>();

        for (int i = 0; i < cxps.size(); i++) {
            data.add(repository.getHigestTestFailureForAProduct(cxps.get(i)));

        }
        List<JenkinsData> dataList = (List<JenkinsData>) data.get(0);

        if(dataList.isEmpty()) return null;
        else return dataList.get(0);
    }

    public static List<JenkinsData> getListOfJenkinsFromPeriodENM(ProjectRepository repository, Date start, Date end){
        return repository.getJenkinsDataFromPeriod(start, end);
    }

    public static List<JenkinsData> getListOfJenkinsFromPeriodAProduct(ProjectRepository repository, Date start, Date end, String productNumber){
        return repository.getJenkinsDataFromPeriodForAProduct(start, end, productNumber);
    }

    public static List<Object> getListOfJenkinsFromPeriodCNA(ProjectRepository repository, Date start, Date end, String cnaName){
        List<String> cxps = getCxpsFromCna(repository, cnaName);
        List<Object> data = new ArrayList<>();

        for (int i = 0; i < cxps.size(); i++) {
            data.add(repository.getJenkinsDataFromPeriodForAProduct(start, end, cxps.get(i)));
        }
        return data;
    }

    public static List<JenkinsData> getAlertsForJobs(ProjectRepository repository) {
        LocalDate start = LocalDate.MIN;
        LocalDate end = LocalDate.now().minusDays(7);

        return getListOfJenkinsFromPeriodENM(repository, Date.valueOf(start), Date.valueOf(end));
    }

}
