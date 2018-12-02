package com.qianfeng.qf.v7.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:consumer.xml")
public class QfV7SearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(QfV7SearchApplication.class, args);
	}
}
