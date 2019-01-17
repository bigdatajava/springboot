package com.itmayiedu;

import com.itmayiedu.dbconfig.DBConfig1;
import com.itmayiedu.dbconfig.DBConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })
@SpringBootApplication
public class AtomikosApp {

	public static void main(String[] args) {
		SpringApplication.run(AtomikosApp.class, args);
	}

}
