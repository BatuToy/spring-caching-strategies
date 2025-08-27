package com.btoy.cache.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching(proxyTargetClass = false)
@EnableJpaRepositories(basePackages= {"com.btoy.cache.demo.dao"})
@SpringBootApplication(scanBasePackages = "com.btoy.cache.demo")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		// todo ->  How to start with Command Line Runner ??
	}



}
