package com.example.microservices.HystrixDashBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.example.microservices.HystrixDashBoard")
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HystrixDashBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashBoardApplication.class, args);
	}

}
