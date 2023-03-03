package com.cpan252.tekkenreborn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cpan252.tekkenreborn.model.dto.FighterSearchByDateDto;
import com.cpan252.tekkenreborn.model.dto.FighterSearchByAnime;
import com.cpan252.tekkenreborn.repository.FighterRepository;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
@Controller
@RequestMapping("/fighterlist")
public class FighterListController {
    private FighterRepository repository;

    public FighterListController(FighterRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showFighters(Model model) {
        return "fighterlist";
    }

    @ModelAttribute
    public void fighters(Model model) {
        model.addAttribute("fighters", repository.findAll());
    }

    @ModelAttribute
    public void fightersByDateDto(Model model) {
        model.addAttribute("fightersByDateDto", new FighterSearchByDateDto());
    }
    @PostMapping
    public String searchFightersByDate(@ModelAttribute FighterSearchByDateDto fightersByDateDto,
                                       Model model) {
        var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        model.addAttribute("fighters",
                repository.findByNameStartsWithAndCreatedAtBetween(fightersByDateDto.getName(),
                        LocalDate.parse(fightersByDateDto.getStartDate(), dateFormatter),
                        LocalDate.parse(fightersByDateDto.getEndDate(), dateFormatter)));
        return "fighterlist";
    }
/*
    @ModelAttribute
    public void fightersByAnime(Model model) {
        model.addAttribute("fightersByAnime", new FighterSearchByAnime());
    }

    @PostMapping
    public String searchFightersByAnime(@ModelAttribute FighterSearchByAnime fightersByAnime,
                                       Model model) {
        model.addAttribute("fighters", repository.findByAnimeFrom(fightersByAnime.getAnimeFrom()));
        return "fighterlist";
    }*/
}
