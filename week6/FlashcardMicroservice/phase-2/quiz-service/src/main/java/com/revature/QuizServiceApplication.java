package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
 * In order to make your service discoverable, add Consul Discovery Agent to the dependencies
 * The apply the annotation below...
 */

@EnableDiscoveryClient
@SpringBootApplication
public class QuizServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizServiceApplication.class, args);
	}

}
