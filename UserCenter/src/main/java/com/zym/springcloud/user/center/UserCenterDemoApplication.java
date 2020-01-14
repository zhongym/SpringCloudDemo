package com.zym.springcloud.user.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync(/*mode = AdviceMode.ASPECTJ*/)
//@EnableLoadTimeWeaving
@EnableScheduling
public class UserCenterDemoApplication {

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream(new File("/out.txt"), "utf-8"));

        SpringApplication.run(UserCenterDemoApplication.class, args);
        List<Object> objects = new ArrayList<>();
        objects.add(1);

        List<?> a = objects;
    }
}
