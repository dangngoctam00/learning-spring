package com.example.springcloudconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConsumeApplication.class, args);
    }

}
