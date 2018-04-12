package com.irenailieva.nutricounter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NutricounterApplication {
	public static void main(String[] args) {
		SpringApplication.run(NutricounterApplication.class, args);
	}
}
