package com.cpan252.tekkenreborn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cpan252.tekkenreborn.model.Fighter;
import com.cpan252.tekkenreborn.model.Fighter.Anime;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long> {
    List<Fighter> findByAnimeFrom(Anime animeFrom);

    List<Fighter> findByNameStartsWithAndCreatedAtBetween(String namePrefix, LocalDate fromDate, LocalDate toDate);
}
