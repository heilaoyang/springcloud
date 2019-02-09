package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = {"com.login.dao"})
@EnableTransactionManagement
@EnableCaching
@EnableSwagger2
@EnableDiscoveryClient
@RefreshScope
public class App1 {

	public static void main(String[] args) {
		SpringApplication.run(App1.class, args);
	}
}
