package com.cpan252.tekkenreborn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			repository.save(new Fighter("Jason", 89,1010,5,Anime.TEKKEN));
			repository.save(new Fighter("Tom", 81,3050,9,Anime.NARUTO));
			repository.save(new Fighter("Brian", 94,3333,9,Anime.DRAGON_BALL));
			repository.save(new Fighter("Kevin", 95,1001,8,Anime.NARUTO));
			repository.save(new Fighter("Andrey", 70,1020,5,Anime.BLEACH));
			repository.save(new Fighter("Vic", 85,3150,6,Anime.DRAGON_BALL));

		};
	}

}
