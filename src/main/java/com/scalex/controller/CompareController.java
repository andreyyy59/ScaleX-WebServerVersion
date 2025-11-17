package com.scalex.controller;

import com.scalex.model.Motorcycle;
import com.scalex.service.MotorcycleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CompareController {

    private final MotorcycleService motorcycleService;

    public CompareController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/compare")
    public String compare(@RequestParam(value = "ids", required = false) String motorcycleIds, Model model) {
        model.addAttribute("currentPage", "compare");
        
        // Obtener todas las motos para los selectores
        List<Motorcycle> allMotorcycles = motorcycleService.findAll();
        model.addAttribute("allMotorcycles", allMotorcycles);
        
        // Si no hay IDs, mostrar página vacía
        if (motorcycleIds == null || motorcycleIds.isEmpty()) {
            model.addAttribute("motorcycles", List.of());
            return "compare";
        }

        // Parsear IDs y obtener motos
        List<Long> ids = Arrays.stream(motorcycleIds.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Motorcycle> motorcycles = ids.stream()
                .map(motorcycleService::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        model.addAttribute("motorcycles", motorcycles);
        return "compare";
    }
}