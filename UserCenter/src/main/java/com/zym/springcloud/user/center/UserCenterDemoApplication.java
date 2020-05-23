package com.zym.springcloud.user.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableAsync(/*mode = AdviceMode.ASPECTJ*/)
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
