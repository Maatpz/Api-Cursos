package com.desafio.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursosApplication.class, args);
		System.out.println("INICIANDO ");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}