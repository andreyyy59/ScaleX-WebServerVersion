package com.scalex.repository;

import com.scalex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);  // Debe devolver Optional<User>
    
    Optional<User> findByEmail(String email);  // También Optional<User>
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}