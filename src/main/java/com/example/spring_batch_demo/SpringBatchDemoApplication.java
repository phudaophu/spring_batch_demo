package com.example.spring_batch_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableBatchProcessing
@ComponentScan({"com.example.spring_batch_demo.service","com.example.spring_batch_demo.config","com.example.spring_batch_demo.listener"
		,"com.example.spring_batch_demo.repository","com.example.spring_batch_demo.reader"
		,"com.example.spring_batch_demo.controller"
		,"com.example.spring_batch_demo.processor","com.example.spring_batch_demo.writer"})
@EnableAsync
@EnableScheduling
public class SpringBatchDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBatchDemoApplication.class, args);
	}

}
