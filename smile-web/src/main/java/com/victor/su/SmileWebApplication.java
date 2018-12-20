package com.victor.su;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SmileWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmileWebApplication.class, args);
	}

}

