package com.example.springcloudclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudClientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudClientsApplication.class, args);
	}

}
