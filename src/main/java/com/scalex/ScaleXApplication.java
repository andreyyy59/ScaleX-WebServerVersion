package com.scalex;

import com.scalex.model.Motorcycle;
import com.scalex.model.User;
import com.scalex.repository.MotorcycleRepository;
import com.scalex.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ScaleXApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaleXApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner createTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin")) {
                User user = new User();
                user.setUsername("admin");
                user.setEmail("admin@test.com");
                user.setPassword(passwordEncoder.encode("admin123"));
                userRepository.save(user);
                System.out.println("✅ Usuario de prueba creado: admin / admin123");
            }
        };
    }
    
    @Bean
    public CommandLineRunner createTestMotorcycles(MotorcycleRepository motorcycleRepository) {
        return args -> {
            if (motorcycleRepository.count() == 0) {
                System.out.println("🏍️ Creando motos de prueba...");
                
                // Honda
                createMotorcycle(motorcycleRepository, "Honda", "Access 125", 2023, "Scooter", 125, 8, "access_125.png");
                createMotorcycle(motorcycleRepository, "Honda", "ADV 350", 2023, "Adventure", 350, 29, "adv350.png");
                createMotorcycle(motorcycleRepository, "Honda", "Africa Twin", 2023, "Adventure", 1084, 102, "africa_twin.png");
                createMotorcycle(motorcycleRepository, "Honda", "CB125F", 2023, "Street", 125, 11, "cb125f.png");
                createMotorcycle(motorcycleRepository, "Honda", "CB150F", 2023, "Street", 150, 17, "cb150f_2022.png");
                createMotorcycle(motorcycleRepository, "Honda", "CB200X", 2023, "Adventure", 200, 17, "cb200x_2022.png");
                createMotorcycle(motorcycleRepository, "Honda", "CB1000R", 2023, "Naked", 998, 145, "cb1000r.png");
                
                // Bajaj
                createMotorcycle(motorcycleRepository, "Bajaj", "Pulsar 150", 2023, "Street", 150, 14, "pulsar_150.png");
                createMotorcycle(motorcycleRepository, "Bajaj", "Pulsar 220F", 2023, "Street", 220, 20, "pulsar_220f.png");
                createMotorcycle(motorcycleRepository, "Bajaj", "Dominar 250", 2023, "Sport", 250, 27, "dominar_250.png");
                createMotorcycle(motorcycleRepository, "Bajaj", "Dominar 400", 2023, "Sport", 400, 40, "dominar_400.png");
                createMotorcycle(motorcycleRepository, "Bajaj", "CT 100", 2023, "Commuter", 100, 8, "ct_100.png");
                
                // TVS
                createMotorcycle(motorcycleRepository, "TVS", "Apache RTR 310", 2023, "Sport", 310, 35, "apache_rr310.png");
                createMotorcycle(motorcycleRepository, "TVS", "Apache TTR 160", 2023, "Sport", 160, 15, "apache_rtr_160_2022.png");
                createMotorcycle(motorcycleRepository, "TVS", "Raider 125", 2023, "Sport", 125, 12, "raider_125.png");
                	
                // BMW
                createMotorcycle(motorcycleRepository, "BMW", "C 400 GT", 2023, "Scooter", 350, 34, "c_400_gt.png");
                createMotorcycle(motorcycleRepository, "BMW", "F 750 GS", 2023, "Adventure", 750, 77, "f_750_gs_2022.png");
                createMotorcycle(motorcycleRepository, "BMW", "G 310 R", 2023, "Naked", 313, 34, "g_310_r_2022.png");
                createMotorcycle(motorcycleRepository, "BMW", "K 1600 B", 2023, "Touring", 1649, 160, "k_1600_b.png");
                
                // Suzuki
                createMotorcycle(motorcycleRepository, "Suzuki", "Boulevard C50", 2023, "Cruiser", 805, 55, "boulevard_c50.png");
                createMotorcycle(motorcycleRepository, "Suzuki", "Concours 14", 2023, "Sport Touring", 1352, 160, "concours_14.png");
                createMotorcycle(motorcycleRepository, "Suzuki", "Gixxer 250 SF", 2023, "Sport", 250, 26, "gixxer_250_sf.png");
                createMotorcycle(motorcycleRepository, "Suzuki", "GN 125", 2023, "Street", 125, 10, "suzuki_gn_125.png");
                
                // Hero
                createMotorcycle(motorcycleRepository, "Hero", "Hunk 150", 2023, "Street", 150, 14, "hero_hunk_150.png");
                
                System.out.println("✅ " + motorcycleRepository.count() + " motos de prueba creadas");
            } else {
                System.out.println("ℹ️ Ya existen " + motorcycleRepository.count() + " motos en la BD");
            }
        };
    }
    
    private void createMotorcycle(MotorcycleRepository repository, String make, String model, 
                                Integer year, String category, Integer engineSize, 
                                Integer power, String imageName) {
        // Crear directamente sin verificar duplicados (para simplificar)
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setMake(make);
        motorcycle.setModel(model);
        motorcycle.setYear(year);
        motorcycle.setCategory(category);
        motorcycle.setEngineSize(engineSize);
        motorcycle.setPower(power);
        motorcycle.setImageUrl("images/motorcycles/" + imageName);
        repository.save(motorcycle);
        System.out.println("✅ " + make + " " + model + " creada");
    }
}