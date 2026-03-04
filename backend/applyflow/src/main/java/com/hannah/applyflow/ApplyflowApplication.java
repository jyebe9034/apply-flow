package com.hannah.applyflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화라는데.. TODO: 여기에도 붙는게 맞는건지 확인 필요
@SpringBootApplication
public class ApplyflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplyflowApplication.class, args);
	}

}
