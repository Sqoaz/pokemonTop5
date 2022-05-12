package com.alea.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PokemonApplication {

	@Bean
	public RestTemplate getRestTempalte(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(PokemonApplication.class, args);
	}

}
