package com.zym.springcloud.user.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
public class UserCenterDemoApplication {

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream(new File("/out.txt"), "utf-8"));

        SpringApplication.run(UserCenterDemoApplication.class, args);
    }
}
