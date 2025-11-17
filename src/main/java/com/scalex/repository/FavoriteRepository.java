package com.scalex.repository;

import com.scalex.model.Favorite;
import com.scalex.model.User;
import com.scalex.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndMotorcycle(User user, Motorcycle motorcycle);
    boolean existsByUserAndMotorcycle(User user, Motorcycle motorcycle);
    void deleteByUserAndMotorcycle(User user, Motorcycle motorcycle);
}