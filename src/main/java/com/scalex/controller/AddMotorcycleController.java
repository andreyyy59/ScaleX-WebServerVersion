package com.scalex.controller;

import com.scalex.model.Motorcycle;
import com.scalex.service.MotorcycleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/form-motorcycles")
public class AddMotorcycleController {

    private final MotorcycleService motorcycleService;
    
    public AddMotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("motorcycle", new Motorcycle());
        return "add-motorcycle";
    }

    @PostMapping("/add")
    public String addMotorcycle(@RequestParam String make,
                               @RequestParam String model,
                               @RequestParam Integer year,
                               @RequestParam String category,
                               @RequestParam Integer engineSize,
                               @RequestParam Integer power,
                               @RequestParam(required = false) String imageUrl) {
        
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setMake(make);
        motorcycle.setModel(model);
        motorcycle.setYear(year);
        motorcycle.setCategory(category);
        motorcycle.setEngineSize(engineSize);
        motorcycle.setPower(power);
        motorcycle.setImageUrl(imageUrl != null ? imageUrl : "/images/motorcycles/default.jpg");
        
        motorcycleService.save(motorcycle);
        return "redirect:/home";
    }
}