package com.hackathon.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class HktGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HktGatewayApplication.class, args);
	}
}
