package com.cpan252.tekkenreborn.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table
public class Fighter {
    public enum Anime {
        NARUTO("Naruto"), BLEACH("Bleach"), TEKKEN("Tekken"), ONE_PIECE("One Piece"),
        FULL_METAL_ALCHEMIST("Full Metal Alchemist"), DRAGON_BALL("Dragon Ball");

        private String title;

        private Anime(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    @Id
    private Long id;

    private String name;

    private int damagePerHit;

    private int health;

    private double resistance;
    private Anime animeFrom;

    private LocalDateTime createdAt = LocalDateTime.now();
    public Fighter(){

    }
    public Fighter(String name, int damagePerHit, int health, double resistance, Anime animeFrom) {
        this.createdAt = LocalDateTime.now();
        this.name = name;
        this.damagePerHit = damagePerHit;
        this.health = health;
        this.resistance = resistance;
        this.animeFrom = animeFrom;
    }

    public Fighter(Long id, String name, int damagePerHit, int health, double resistance, Anime animeFrom) {
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.name = name;
        this.damagePerHit = damagePerHit;
        this.health = health;
        this.resistance = resistance;
        this.animeFrom = animeFrom;
    }
}
