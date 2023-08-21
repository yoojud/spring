package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication

// 컨트롤러위치
@ComponentScan(basePackages = {"com.example.controller"})

// 엔티티위치
@EntityScan(basePackages = {"com.example.entity"})

// 저장소위치
@EnableMongoRepositories(basePackages = "com.example.repository")

public class Application {

	public static void main(String[] args) {
		System.out.println("web server start");
		SpringApplication.run(Application.class, args);
	}
}
