package com.ericsson.testreporttool;

import com.ericsson.testreporttool.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import java.util.*;

import com.ericsson.testreporttool.jenkinsfunctions.TestData;

@SpringBootApplication
public class TestreporttoolApplication {

	@Autowired
	ProjectRepository repository;

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(TestreporttoolApplication.class, args);
		ProductService service = context.getBean(ProductService.class);
	}

	@Bean
	@Scope("application")
	List<TestData> testData(){
		List<TestData> data = new ArrayList<>();
		data.add(new TestData("https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/transport-api_PCR/", 75,2, 1234567890));
		data.add(new TestData("https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/transport-api_PCR/", 51,0, 1234567890));
		data.add(new TestData("https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/transport-api_PCR/", 87,0, 1234567890));
		data.add(new TestData("https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/transport-api_PCR/", 23,6, 1234567890));
		data.add(new TestData("https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/transport-api_PCR/", 90,1, 1234567890));
		data.add(new TestData("https://fem23s11-eiffel004.eiffel.gic.ericsson.se:8443/jenkins/job/transport-api_PCR/", 53,0, 1234567890));
		return data;
	}

	@Bean
	@Scope("application")
	Map<String, Projects> products() {
		List<Projects> projects = ProductService.getAllCXP(repository);
		Map<String, Projects> projectsMap = new HashMap<>();
		for(Projects p: projects){
			projectsMap.put(p.getNumber(), p);
		}
		return projectsMap;
	}






}
