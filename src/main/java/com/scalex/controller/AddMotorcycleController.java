package com.scalex.controller;

import com.scalex.model.Motorcycle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/form-motorcycles")
public class AddMotorcycleController {

    // Lista en memoria para almacenar las motos
    private List<Motorcycle> motorcycles = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong(1); // Para generar IDs únicos

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
                               @RequestParam(required = false) String imageUrl,
                               Model viewModel) {
        
        // Crear nueva moto
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId(nextId.getAndIncrement()); // Asignar ID único
        motorcycle.setMake(make);
        motorcycle.setModel(model);
        motorcycle.setYear(year);
        motorcycle.setCategory(category);
        motorcycle.setEngineSize(engineSize);
        motorcycle.setPower(power);
        motorcycle.setImageUrl(imageUrl != null ? imageUrl : "/images/motorcycles/default.jpg");
        
        // Guardar en lista en memoria
        motorcycles.add(motorcycle);
        
        // Agregar lista al modelo para mostrar
        viewModel.addAttribute("motorcycles", motorcycles);
        
        return "redirect:/form-motorcycles/list";
    }

    // Nueva ruta para listar las motos
    @GetMapping("/list")
    public String listMotorcycles(Model model) {
        model.addAttribute("motorcycles", motorcycles);
        return "motorcycle-list";
    }

    // Eliminar moto
    @GetMapping("/delete/{id}")
    public String deleteMotorcycle(@PathVariable Long id) {
        motorcycles.removeIf(moto -> moto.getId().equals(id));
        return "redirect:/form-motorcycles/list";
    }
}