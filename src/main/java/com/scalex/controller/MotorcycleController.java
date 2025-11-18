package com.scalex.controller;

import com.scalex.model.Motorcycle;
import com.scalex.service.MotorcycleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/motorcycles")
public class MotorcycleController {

    private final MotorcycleService motorcycleService;
    
    public MotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("motorcycle", new Motorcycle());
        return "add-motorcycle";
    }

    @PostMapping("/add")
    public String addMotorcycle(@ModelAttribute Motorcycle motorcycle,
                               @RequestParam("imageFile") MultipartFile imageFile,
                               RedirectAttributes redirectAttributes) {
        try {
            // Guardar la imagen si se proporciona
            if (!imageFile.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
                Path path = Paths.get("src/main/resources/static/images/motorcycles/" + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, imageFile.getBytes());
                motorcycle.setImageUrl("/images/motorcycles/" + fileName);
            } else {
                motorcycle.setImageUrl("/images/motorcycles/default.jpg");
            }

            motorcycleService.save(motorcycle);
            redirectAttributes.addFlashAttribute("success", "Moto agregada exitosamente!");
            
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la imagen: " + e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al agregar la moto: " + e.getMessage());
        }

        return "redirect:/home";
    }
}