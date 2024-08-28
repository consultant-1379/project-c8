package com.ericsson.testreporttool.database.parser;

import com.ericsson.testreporttool.database.Product;
import com.ericsson.testreporttool.database.Projects;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParser {

    public List<Projects> parseCXP(){
        ClassPathResource classPathResource = new ClassPathResource("CXP.csv");

        try{
            InputStream inputStream = classPathResource.getInputStream();
            File file = File.createTempFile("CXP", ".csv");
            FileUtils.copyInputStreamToFile(inputStream, file);
            CSVReader reader=
                    new CSVReaderBuilder(new FileReader(file)).
                            withSkipLines(1). // Skiping firstline as it is header
                            build();
            List<Projects> csv_objectList =reader.readAll().stream().map(data-> {
                Projects projects = new Projects();
                projects.setCSA(data[0]);
                projects.setCRA(data[1]);
                projects.setCNA(data[2]);
                projects.setCXP(data[3]);
                projects.setNumber(data[4]);
                projects.setRPM(data[5]);
                projects.setTAF(data[6]);
                return projects;
            }).collect(Collectors.toList());
            return csv_objectList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> parse(String filename){
        ClassPathResource classPathResource = new ClassPathResource(filename);

        try{
            InputStream inputStream = classPathResource.getInputStream();
            File file = File.createTempFile("tmp", ".csv");
            FileUtils.copyInputStreamToFile(inputStream, file);
            CSVReader reader=
                    new CSVReaderBuilder(new FileReader(file)).
                            withSkipLines(1). // Skiping firstline as it is header
                            build();
            List<Product> csv_objectList =reader.readAll().stream().map(data-> {
                Product prod = new Product();
                prod.setName(data[0]);
                prod.setNumber(data[1]);
                return prod;
            }).collect(Collectors.toList());
            return csv_objectList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
