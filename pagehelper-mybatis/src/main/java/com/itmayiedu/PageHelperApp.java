package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.itmayiedu.mapper")
@SpringBootApplication
public class PageHelperApp {

	public static void main(String[] args) {
		SpringApplication.run(PageHelperApp.class, args);
	}

}
