package com.qianfeng.qf.v7.index;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:consumer.xml")
public class QfV7IndexApplication {

	public static void main(String[] args) {
		SpringApplication.run(QfV7IndexApplication.class, args);
	}
}
