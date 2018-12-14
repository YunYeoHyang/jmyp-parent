package com.czxy.jmyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WebEurekaServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run( WebEurekaServiceApplication.class ,args );
    }
}

