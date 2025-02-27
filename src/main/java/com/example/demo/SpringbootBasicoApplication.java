package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringbootBasicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBasicoApplication.class, args);
	}

}
