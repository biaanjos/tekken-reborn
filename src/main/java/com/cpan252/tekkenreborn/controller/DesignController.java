package com.cpan252.tekkenreborn.controller;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.cpan252.tekkenreborn.model.Fighter;
import com.cpan252.tekkenreborn.model.Fighter.Anime;
import com.cpan252.tekkenreborn.repository.FighterRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/design")
public class DesignController {

    @Autowired
    private FighterRepository repository;

    @GetMapping
    public String design() {
        return "design";
    }

    @ModelAttribute
    public void animes(Model model) {
        var animes = EnumSet.allOf(Anime.class);
        model.addAttribute("animes", animes);
    }

    @ModelAttribute
    public Fighter fighter() {
        return new Fighter(null, 0,0,0,null);
    }


    @PostMapping
    public String submitForm(Fighter fighter) {
        if (fighter.getName() == null || fighter.getHealth() < 1000 || fighter.getDamagePerHit()  > 100 || fighter.getResistance() < 0 || fighter.getResistance() > 10 ){
            return "error";
        }
        repository.save(fighter);
        return "redirect:/fighterlist";
    }

}
