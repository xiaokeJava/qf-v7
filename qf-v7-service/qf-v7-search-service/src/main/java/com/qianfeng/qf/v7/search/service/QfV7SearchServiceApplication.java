package com.qianfeng.qf.v7.search.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:provider.xml")
@MapperScan("com.qianfeng.v6.mapper")
public class QfV7SearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QfV7SearchServiceApplication.class, args);
	}
}
