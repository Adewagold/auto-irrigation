package com.banque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalTime;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@EnableRetry
public class AutomaticIrrigationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AutomaticIrrigationApplication.class, args);
	}

}
