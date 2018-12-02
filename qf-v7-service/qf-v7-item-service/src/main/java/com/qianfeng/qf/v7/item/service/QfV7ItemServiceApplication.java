package com.qianfeng.qf.v7.item.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:provider.xml")
public class QfV7ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QfV7ItemServiceApplication.class, args);
	}
}
