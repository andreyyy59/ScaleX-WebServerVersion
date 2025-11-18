package com.scalex.config;

import com.scalex.model.Motorcycle;
import com.scalex.model.User;
import com.scalex.repository.MotorcycleRepository;
import com.scalex.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer {

    private final MotorcycleRepository motorcycleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(MotorcycleRepository motorcycleRepository, 
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.motorcycleRepository = motorcycleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("🚀 Iniciando DataInitializer...");
        createTestUser();
        createTestMotorcycles();
        System.out.println("✅ DataInitializer completado");
    }

    private void createTestUser() {
        System.out.println("⏳ Verificando usuario admin...");
        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("admin@test.com");
            user.setPassword(passwordEncoder.encode("admin123"));
            userRepository.save(user);
            System.out.println("✅ Usuario creado: admin / admin123");
        } else {
            System.out.println("ℹ️ Usuario admin ya existe");
        }
    }

    private void createTestMotorcycles() {
        System.out.println("⏳ Verificando motos...");
        long count = motorcycleRepository.count();
        System.out.println("📊 Motos actuales en BD: " + count);

        if (count == 0) {
            System.out.println("🏍️ Creando 10 motos de prueba...");

            try {
                createMotorcycle("Yamaha", "YZF-R1", 2023, "Deportiva", 998, 200, "1e3c72");
                createMotorcycle("Honda", "CBR1000RR", 2023, "Deportiva", 999, 189, "dc2626");
                createMotorcycle("Kawasaki", "Ninja ZX-10R", 2023, "Deportiva", 998, 203, "16a34a");
                createMotorcycle("Suzuki", "GSX-R1000", 2023, "Deportiva", 999, 199, "3b82f6");
                createMotorcycle("Ducati", "Panigale V4", 2023, "Deportiva", 1103, 214, "dc2626");
                createMotorcycle("Yamaha", "MT-09", 2023, "Naked", 889, 117, "2a5298");
                createMotorcycle("Kawasaki", "Z900", 2023, "Naked", 948, 125, "16a34a");
                createMotorcycle("KTM", "890 Duke", 2023, "Naked", 890, 115, "f97316");
                createMotorcycle("BMW", "R 1250 GS", 2023, "Adventure", 1254, 136, "3b82f6");
                createMotorcycle("Honda", "Africa Twin", 2023, "Adventure", 1084, 102, "dc2626");

                long finalCount = motorcycleRepository.count();
                System.out.println("🎉 Total de motos creadas: " + finalCount);
            } catch (Exception e) {
                System.err.println("❌ Error al crear motos: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("ℹ️ Ya existen " + count + " motos en la BD");
        }
    }

    private void createMotorcycle(String make, String model, int year, String category, 
                                 int engineSize, int power, String color) {
        Motorcycle m = new Motorcycle();
        m.setMake(make);
        m.setModel(model);
        m.setYear(year);
        m.setCategory(category);
        m.setEngineSize(engineSize);
        m.setPower(power);
        m.setImageUrl("https://via.placeholder.com/400x300/" + color + "/ffffff?text=" + 
                     make.replace(" ", "+") + "+" + model.replace(" ", "+"));
        motorcycleRepository.save(m);
        System.out.println("  ✅ " + make + " " + model);
    }
}