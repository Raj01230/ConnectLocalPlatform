package com.dollop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootSecurityJwt4Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJwt4Application.class, args);
	}

}
