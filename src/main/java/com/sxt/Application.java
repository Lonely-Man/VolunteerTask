package com.sxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class} )
@MapperScan(basePackages = { "com.sxt.*.mapper" })
@EnableScheduling

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
