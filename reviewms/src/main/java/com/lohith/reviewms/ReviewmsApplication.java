package com.lohith.reviewms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReviewmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewmsApplication.class, args);
	}

}
