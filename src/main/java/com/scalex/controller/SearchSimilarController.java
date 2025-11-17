package com.scalex.controller;

import com.scalex.model.Motorcycle;
import com.scalex.service.MotorcycleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchSimilarController {

    private final MotorcycleService motorcycleService;

    public SearchSimilarController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/search-similar")
    public String searchSimilar(@RequestParam(required = false) Long baseMotorcycleId,
                              @RequestParam(required = false) String category,
                              @RequestParam(required = false) Double minEngineSize,
                              @RequestParam(required = false) Double maxEngineSize,
                              Model model) {
        
        model.addAttribute("currentPage", "search-similar");
        
        // Obtener todas las motos para el selector
        List<Motorcycle> allMotorcycles = motorcycleService.findAll();
        model.addAttribute("allMotorcycles", allMotorcycles);

        // Si no hay ID base, mostrar página vacía
        if (baseMotorcycleId == null) {
            model.addAttribute("similarMotorcycles", new ArrayList<>());
            return "search-similar";
        }

        var baseMotorcycle = motorcycleService.findById(baseMotorcycleId)
                .orElseThrow(() -> new IllegalArgumentException("Motorcycle not found"));

        List<Motorcycle> similarMotorcycles = motorcycleService.searchMotorcycles(
                null, null, category != null ? category : baseMotorcycle.getCategory());

     // Filtrar por tamaño de motor si se especifica
        if (minEngineSize != null || maxEngineSize != null) {
            similarMotorcycles = similarMotorcycles.stream()
                    .filter(m -> m.getId() != null && !m.getId().equals(baseMotorcycleId))
                    .filter(m -> {
                        int engineSize = m.getEngineSize(); // Es int primitivo, no necesita null check
                        boolean minOk = minEngineSize == null || engineSize >= minEngineSize.intValue();
                        boolean maxOk = maxEngineSize == null || engineSize <= maxEngineSize.intValue();
                        return minOk && maxOk;
                    })
                    .collect(java.util.stream.Collectors.toList());
        } else {
            // Excluir la moto base si no hay filtros
            similarMotorcycles = similarMotorcycles.stream()
                    .filter(m -> m.getId() != null && !m.getId().equals(baseMotorcycleId))
                    .collect(java.util.stream.Collectors.toList());
        }

        model.addAttribute("baseMotorcycle", baseMotorcycle);
        model.addAttribute("similarMotorcycles", similarMotorcycles);
        return "search-similar";
    }
}