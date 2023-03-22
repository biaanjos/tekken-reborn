package com.cpan252.tekkenreborn.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cpan252.tekkenreborn.model.dto.FighterSearchByDateDto;
import com.cpan252.tekkenreborn.model.dto.FighterSearchByAnime;
import com.cpan252.tekkenreborn.repository.FighterRepository;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.cpan252.tekkenreborn.model.User;
@Controller
@RequestMapping("/fighterlist")
public class FighterListController {
    private FighterRepository repository;
    private static final int PAGE_SIZE = 5;
    public FighterListController(FighterRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showFighters(Model model) {
        return "fighterlist";
    }

    @ModelAttribute
    public void fighters(Model model) {
        var fighterPage = repository.findAll(PageRequest.of(0, PAGE_SIZE));
        model.addAttribute("fighters", fighterPage);
        model.addAttribute("currentPage", fighterPage.getNumber());
        model.addAttribute("totalPages", fighterPage.getTotalPages());
        //model.addAttribute("fighters", repository.findAll());
    }

    @GetMapping("/switchPage")
    public String switchPage(Model model,
                             @RequestParam("pageToSwitch") Optional<Integer> pageToSwitch) {
        var page = pageToSwitch.orElse(0);
        var totalPages = (int) model.getAttribute("totalPages");
        if (page < 0 || page >= totalPages) {
            return "fighterlist";
        }
        var fighterPage = repository.findAll(PageRequest.of(pageToSwitch.orElse(0),
                PAGE_SIZE));
        model.addAttribute("fighters", fighterPage.getContent());
        model.addAttribute("currentPage", fighterPage.getNumber());
        return "fighterlist";
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

    @PostMapping("/deleteAllFighters")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String processFightersDeletion(@AuthenticationPrincipal User user) {
        repository.deleteAll();
        return "redirect:/fighterlist";
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
