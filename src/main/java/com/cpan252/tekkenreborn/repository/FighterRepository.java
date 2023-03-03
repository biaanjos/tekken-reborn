package com.cpan252.tekkenreborn.repository;

import org.springframework.data.repository.CrudRepository;
import com.cpan252.tekkenreborn.model.Fighter;
import com.cpan252.tekkenreborn.model.Fighter.Anime;
import java.time.LocalDate;
import java.util.List;

public interface FighterRepository extends CrudRepository<Fighter, Long> {
    List<Fighter> findByAnimeFrom(Anime animeFrom);

    List<Fighter> findByNameStartsWithAndCreatedAtBetween(String namePrefix, LocalDate fromDate, LocalDate toDate);
}
