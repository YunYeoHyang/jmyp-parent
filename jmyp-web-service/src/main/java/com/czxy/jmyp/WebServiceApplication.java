package com.czxy.jmyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run( WebServiceApplication.class , args );
    }
}