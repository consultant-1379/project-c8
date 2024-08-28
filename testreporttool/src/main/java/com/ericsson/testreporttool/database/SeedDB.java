package com.ericsson.testreporttool.database;

import com.ericsson.testreporttool.database.parser.CSVParser;
import com.ericsson.testreporttool.jenkinsfunctions.JenkinsAccess;
import com.ericsson.testreporttool.jenkinsfunctions.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

/**
 * Populate the Database
 */

@Component
public class SeedDB {

    @Autowired
    JdbcTemplate template;

    @Autowired
    ProjectRepository repository;

    @PostConstruct
    public void init() {
        // obtain data
        CSVParser csvParser = new CSVParser();
        JenkinsAccess j = new JenkinsAccess();
        List<Projects> cxpList = csvParser.parseCXP();
        List<Product> cnaList = csvParser.parse("CNA.csv");
        List<Product> craList = csvParser.parse("CRA.csv");
        List<Product> csaList = csvParser.parse("CSA.csv");

        /**
         * CREATING AND POPULATING TABLES
         */
        if (!isTableExist("CNA")){
            template.execute("create table CNA (name varchar(100), number varchar(50))");
            for (Product cna: cnaList){
                template.update("insert into CNA (name, number) values (?, ?)", new Object[]{cna.getName(), cna.getNumber()});
            }
        }
        if (!isTableExist("CSA")){
            template.execute("create table CSA (name varchar(100), number varchar(50))");
            for (Product csa: csaList){
                template.update("insert into CSA (name, number) values (?, ?)", new Object[]{csa.getName(), csa.getNumber()});
            }
        }
        if (!isTableExist("CRA")){
            template.execute("create table CRA (name varchar(100), number varchar(50))");
            for (Product cra: craList){
                template.update("insert into CRA (name, number) values (?, ?)", new Object[]{cra.getName(), cra.getNumber()});
            }
        }
        if (!isTableExist("CXP")){
            template.execute("create table CXP (CSA varchar(100), CRA varchar(100), CNA varchar(100), " +
                    "CXP varchar(100), Number varchar(50), RPM varchar(200), TAF varchar(100))");
            for (Projects cxp: cxpList){
                template.update("insert into CXP (CSA, CRA, CNA, CXP, Number, RPM, TAF) values (?, ?, ?, ?, ?, ?, ?)"
                        , new Object[]{cxp.getCSA(), cxp.getCRA(), cxp.getCNA(), cxp.getCXP(), cxp.getNumber(),
                        cxp.getRPM(), cxp.getTAF()});
            }
        }
        if (!isTableExist("Jenkins")) {
            List<TestData> jenkinsList = j.getTestResults();
            template.execute("create table Jenkins (Number varchar(50), TestURLs varchar(200), Pass int, Fail int, Date DATETIME(2))");
            for (TestData testData : jenkinsList) {
                String number = repository.parseJenkinsUrl(testData.getUrl());
                if (Objects.equals(number, "0")) continue;


                else {
                    template.update("insert into Jenkins(Number, TestURLs, Pass, Fail, Date) values (?, ?, ?, ?, ?)",
                            number,
                            testData.getUrl(),
                            testData.getTestsPassed(),
                            testData.getTestsFailed(),
                            testData.getDateBuilt());
                }
            }
        }
    }

    public boolean isTableExist(String tableName) {
        String query =
                "select count(*) "
                        + "from information_schema.tables "
                        + "where table_name = ?";

        Integer result = template.queryForObject(query, Integer.class, tableName);
        return result == 1 ? true : false;

    }

}
