package com.cpan252.tekkenreborn;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.cpan252.tekkenreborn.controller.HomeController;
import com.cpan252.tekkenreborn.model.Fighter;
import com.cpan252.tekkenreborn.model.Fighter.Anime;
import com.cpan252.tekkenreborn.repository.FighterRepository;

@SpringBootApplication
public class TekkenrebornApplication {

	public static void main(String[] args) {
		SpringApplication.run(TekkenrebornApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(FighterRepository repository) {
		return args -> {
			repository.save(new Fighter("Aaron", 95,1001,8,Anime.NARUTO));
			repository.save(new Fighter("Dustin", 80,2050,7,Anime.BLEACH));
			repository.save(new Fighter("Ethan", 85,3000,6,Anime.DRAGON_BALL));

		};
	}

}
