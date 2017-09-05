package com.zym.springcloud.activity.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ActivityCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityCenterApplication.class, args);
	}
}
