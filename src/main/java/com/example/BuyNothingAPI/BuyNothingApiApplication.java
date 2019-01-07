package com.example.BuyNothingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing
public class BuyNothingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyNothingApiApplication.class, args);
	}

}

