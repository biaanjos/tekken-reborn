package com.cpan252.tekkenreborn.repository;

import org.springframework.data.repository.CrudRepository;

import com.cpan252.tekkenreborn.model.Fighter;

public interface FighterRepository extends CrudRepository<Fighter, Long> {
}
