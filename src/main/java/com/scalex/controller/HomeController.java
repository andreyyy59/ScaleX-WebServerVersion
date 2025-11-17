package com.scalex.controller;

import com.scalex.service.MotorcycleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    private final MotorcycleService motorcycleService;
    
    public HomeController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        // Obtener todas las motos y agregarlas al modelo
        model.addAttribute("motorcycles", motorcycleService.findAll());
        return "home";
    }
}