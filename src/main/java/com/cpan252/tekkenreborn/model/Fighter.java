package com.cpan252.tekkenreborn.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Fighter {
    public enum Anime {
        NARUTO("Naruto"), BLEACH("Bleach"), TEKKEN("Tekken"), ONE_PIECE("One Piece"),
        FULL_METAL_ALCHEMIST("Full Metal Alchemist"), DRAGON_BALL("Dragon Ball");

        @JsonValue
        private String title;

        private Anime(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int damagePerHit;

    private int health;

    private double resistance;
    private Anime animeFrom;

    @Builder.Default
    private LocalDate createdAt = LocalDate.now();

    public Fighter(String name, int damagePerHit, int health, double resistance, Anime animeFrom) {
        this.createdAt = LocalDate.now();
        this.name = name;
        this.damagePerHit = damagePerHit;
        this.health = health;
        this.resistance = resistance;
        this.animeFrom = animeFrom;
    }

    public Fighter(Long id, String name, int damagePerHit, int health, double resistance, Anime animeFrom) {
        this.id = id;
        this.createdAt = LocalDate.now();
        this.name = name;
        this.damagePerHit = damagePerHit;
        this.health = health;
        this.resistance = resistance;
        this.animeFrom = animeFrom;
    }
}
