package com.rent.rentshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RentshopApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentshopApplication.class, args);
	}
}