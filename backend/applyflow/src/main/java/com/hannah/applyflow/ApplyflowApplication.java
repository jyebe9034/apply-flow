package com.hannah.applyflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ApplyflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplyflowApplication.class, args);
	}

}
