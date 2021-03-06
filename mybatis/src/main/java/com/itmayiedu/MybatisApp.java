package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.itmayiedu.dao")
@SpringBootApplication
public class MybatisApp {

	public static void main(String[] args) {
		SpringApplication.run(MybatisApp.class, args);
	}

}
