package com.test.behoh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.test.behoh")
public class BehohApplication {

	public static void main(String[] args) {
		SpringApplication.run(BehohApplication.class, args);
	}

}
