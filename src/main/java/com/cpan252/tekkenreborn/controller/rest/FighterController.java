package com.cpan252.tekkenreborn.controller.rest;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.cpan252.tekkenreborn.model.Fighter;
import com.cpan252.tekkenreborn.model.dto.CreateFighter;
import com.cpan252.tekkenreborn.repository.FighterRepository;

import jakarta.validation.Valid;

@RestController
// essentially the full path is http://localhost:8080/api/fighters
@RequestMapping(path = "/api/fighters", produces = "application/json")
public class FighterController {

    private final FighterRepository fighterRepository;

    public FighterController(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    @GetMapping
    public Iterable<Fighter> allFighters(@RequestParam("page") Optional<Integer> page,
                                         @RequestParam("size") Optional<Integer> size) {
        if (!page.isPresent() || !size.isPresent()) {
            return fighterRepository.findAll();
        } else {
            return fighterRepository.findAll(PageRequest.of(page.get(), size.get()));
        }
    }

    @DeleteMapping("/{id}")
    public void deleteFighter(@PathVariable("id") Long id) {
        fighterRepository.deleteById(id);
    }

    @PostMapping
    public Fighter createFighter(@Valid @RequestBody CreateFighter fighter) {
        return fighterRepository.save(fighter.toFighter());
    }

    @PatchMapping("/{id}")
    public Fighter patchFighter(@PathVariable("id") Long id,
                                 @Valid @RequestBody CreateFighter fighter) {
        var fighterToUpdate = fighterRepository.findById(id).orElseThrow();
        fighterToUpdate.setName(fighter.getName());
        fighterToUpdate.setAnimeFrom(fighter.getAnimeFrom());
        fighterToUpdate.setDamagePerHit(fighter.getDamagePerHit());
        fighterToUpdate.setHealth(fighter.getHealth());
        fighterToUpdate.setResistance(fighter.getResistance());
        return fighterRepository.save(fighterToUpdate);
    }

    @PutMapping("/{id}")
    public Fighter updateFighter(@PathVariable("id") Long id,
                                 @Valid @RequestBody CreateFighter fighter) {
        var fighter_ = fighterRepository.findById(id).orElseThrow();
        fighter_.setName(fighter.getName());
        fighter_.setAnimeFrom(fighter.getAnimeFrom());
        fighter_.setDamagePerHit(fighter.getDamagePerHit());
        fighter_.setHealth(fighter.getHealth());
        fighter_.setResistance(fighter.getResistance());
        return fighterRepository.save(fighter_);
    }

}