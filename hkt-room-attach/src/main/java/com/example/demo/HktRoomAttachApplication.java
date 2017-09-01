package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class HktRoomAttachApplication {

	public static void main(String[] args) {
		SpringApplication.run(HktRoomAttachApplication.class, args);
	}
}
