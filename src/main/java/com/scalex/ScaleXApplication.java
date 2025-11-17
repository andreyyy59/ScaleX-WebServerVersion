package com.scalex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.scalex.model.User;
import com.scalex.repository.UserRepository;

@SpringBootApplication
public class ScaleXApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaleXApplication.class, args);
    }
    
    // Crear usuario de prueba al iniciar
    @Bean
    public CommandLineRunner createTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                User user = new User();
                user.setUsername("admin");
                user.setEmail("admin@test.com");
                user.setPassword(passwordEncoder.encode("admin123"));
                userRepository.save(user);
                System.out.println("Usuario de prueba creado: admin / admin123");
            }
        };
    }
}