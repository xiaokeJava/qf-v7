package com.qianfeng.qf.v7.background;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:consumer.xml")
@Import(FdfsClientConfig.class)
public class QfV7BackgroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(QfV7BackgroundApplication.class, args);
	}
}
