package com.scalex.config;

import com.scalex.model.Motorcycle;
import com.scalex.model.User;
import com.scalex.repository.MotorcycleRepository;
import com.scalex.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    
    private final UserRepository userRepository;
    private final MotorcycleRepository motorcycleRepository;
    private final PasswordEncoder passwordEncoder;
    
    public DataInitializer(UserRepository userRepository, 
                          MotorcycleRepository motorcycleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostConstruct
    public void init() {
        System.out.println("========================================");
        System.out.println("🚀 INICIANDO DATA INITIALIZER");
        System.out.println("========================================");
        
        initializeUser();
        initializeMotorcycles();
        
        System.out.println("========================================");
        System.out.println("✅ DATA INITIALIZER COMPLETADO");
        System.out.println("========================================");
    }
    
    private void initializeUser() {
        System.out.println("👤 Inicializando usuario...");
        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@test.com");
            user.setPassword(passwordEncoder.encode("admin123"));
            userRepository.save(user);
            System.out.println("✅ Usuario admin creado: admin / admin123");
        } else {
            System.out.println("ℹ️ Usuario admin ya existe");
        }
    }
    
    private void initializeMotorcycles() {
        System.out.println("🏍️ Inicializando motos...");
        
        long count = motorcycleRepository.count();
        System.out.println("📊 Motos en BD: " + count);
        
        if (count > 0) {
            System.out.println("ℹ️ Ya existen motos, no se crean nuevas");
            return;
        }
        
        System.out.println("🔧 Creando 10 motos de prueba...");
        
        createMotorcycle("Yamaha", "YZF-R1", 2023, "Deportiva", 998, 200, "1e3c72", "Yamaha+R1");
        createMotorcycle("Honda", "CBR1000RR", 2023, "Deportiva", 999, 189, "dc2626", "Honda+CBR");
        createMotorcycle("Kawasaki", "Ninja ZX-10R", 2023, "Deportiva", 998, 203, "16a34a", "Kawasaki");
        createMotorcycle("Suzuki", "GSX-R1000", 2023, "Deportiva", 999, 199, "3b82f6", "Suzuki");
        createMotorcycle("Ducati", "Panigale V4", 2023, "Deportiva", 1103, 214, "dc2626", "Ducati");
        createMotorcycle("Yamaha", "MT-09", 2023, "Naked", 889, 117, "2a5298", "MT09");
        createMotorcycle("Kawasaki", "Z900", 2023, "Naked", 948, 125, "16a34a", "Z900");
        createMotorcycle("BMW", "R 1250 GS", 2023, "Adventure", 1254, 136, "3b82f6", "BMW+GS");
        createMotorcycle("Harley-Davidson", "Sportster S", 2023, "Cruiser", 1252, 121, "111827", "Harley");
        createMotorcycle("Indian", "Scout", 2023, "Cruiser", 1133, 100, "6b7280", "Indian");
        
        System.out.println("🎉 TOTAL: " + motorcycleRepository.count() + " motos creadas exitosamente");
    }
    
    private void createMotorcycle(String make, String model, int year, String category, 
                                 int engineSize, int power, String color, String text) {
        try {
            Motorcycle m = new Motorcycle();
            m.setMake(make);
            m.setModel(model);
            m.setYear(year);
            m.setCategory(category);
            m.setEngineSize(engineSize);
            m.setPower(power);
            m.setImageUrl("https://via.placeholder.com/400x300/" + color + "/ffffff?text=" + text);
            motorcycleRepository.save(m);
            System.out.println("  ✅ " + make + " " + model);
        } catch (Exception e) {
            System.err.println("  ❌ Error creando " + make + " " + model + ": " + e.getMessage());
        }
    }
}