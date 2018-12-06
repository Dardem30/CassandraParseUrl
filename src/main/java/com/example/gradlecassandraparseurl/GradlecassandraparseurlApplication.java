package com.example.gradlecassandraparseurl;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GradlecassandraparseurlApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		SpringApplication.run(GradlecassandraparseurlApplication.class, args);
	}
}
