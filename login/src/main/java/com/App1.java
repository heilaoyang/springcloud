package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.login.dao")
@EnableTransactionManagement
@EnableCaching
@EnableSwagger2
public class App1 {

	public static void main(String[] args) {
		SpringApplication.run(App1.class, args);
	}
}
