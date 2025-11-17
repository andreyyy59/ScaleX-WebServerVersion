package com.scalex.service;

import com.scalex.model.Favorite;
import com.scalex.model.User;
import com.scalex.model.Motorcycle;
import com.scalex.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public List<Favorite> getUserFavorites(User user) {
        return favoriteRepository.findByUser(user);
    }

    public Favorite addToFavorites(User user, Motorcycle motorcycle) {
        if (favoriteRepository.existsByUserAndMotorcycle(user, motorcycle)) {
            throw new IllegalArgumentException("Motorcycle already in favorites");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setMotorcycle(motorcycle);

        return favoriteRepository.save(favorite);
    }

    public void removeFromFavorites(User user, Motorcycle motorcycle) {
        favoriteRepository.deleteByUserAndMotorcycle(user, motorcycle);
    }

    public boolean isMotorcycleInFavorites(User user, Motorcycle motorcycle) {
        return favoriteRepository.existsByUserAndMotorcycle(user, motorcycle);
    }

    public Optional<Favorite> findByUserAndMotorcycle(User user, Motorcycle motorcycle) {
        return favoriteRepository.findByUserAndMotorcycle(user, motorcycle);
    }
}