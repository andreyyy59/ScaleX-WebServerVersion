package com.scalex.controller;

import com.scalex.model.User;
import com.scalex.service.FavoriteService;
import com.scalex.service.MotorcycleService;
import com.scalex.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private final FavoriteService favoriteService;
    private final UserService userService;
    private final MotorcycleService motorcycleService;

    public FavoritesController(FavoriteService favoriteService, 
                             UserService userService, 
                             MotorcycleService motorcycleService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
        this.motorcycleService = motorcycleService;
    }

    @GetMapping
    public String getFavorites(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        model.addAttribute("favorites", favoriteService.getUserFavorites(user));
        return "favorites";
    }

    @PostMapping("/add")
    public String addToFavorites(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam Long motorcycleId,
                                RedirectAttributes redirectAttributes) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        var motorcycle = motorcycleService.findById(motorcycleId)
                .orElseThrow(() -> new IllegalArgumentException("Motorcycle not found"));
        
        try {
            favoriteService.addToFavorites(user, motorcycle);
            redirectAttributes.addFlashAttribute("success", "Added to favorites");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/home";
    }

    @PostMapping("/remove")
    public String removeFromFavorites(@AuthenticationPrincipal UserDetails userDetails,
                                     @RequestParam Long motorcycleId) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        var motorcycle = motorcycleService.findById(motorcycleId)
                .orElseThrow(() -> new IllegalArgumentException("Motorcycle not found"));
        
        favoriteService.removeFromFavorites(user, motorcycle);
        return "redirect:/favorites";
    }
}