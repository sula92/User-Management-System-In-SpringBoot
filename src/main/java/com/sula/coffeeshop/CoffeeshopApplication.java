package com.sula.coffeeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CoffeeshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeshopApplication.class, args);
	}

}
