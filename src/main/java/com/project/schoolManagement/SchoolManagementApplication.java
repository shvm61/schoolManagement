package com.project.schoolManagement;

import com.project.schoolManagement.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagementApplication {

	@Autowired
	TestRepository testRepo;

	// @PostConstruct
	// public void addTest() {
	// testRepo.save(Test.builder().name("first").build());
	// }

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);

	}

}
