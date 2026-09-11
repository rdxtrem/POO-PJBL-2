package br.com.semeando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appName", "Sistema Semeando – Gestão Escolar");
        model.addAttribute("version", "0.1.0");
        model.addAttribute("responsavel", "Rosivaldo");
        model.addAttribute("disciplina", "POO - PJBL 2");
        return "index";
    }
}
