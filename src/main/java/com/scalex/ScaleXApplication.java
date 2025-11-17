package com.scalex;

import com.scalex.model.Motorcycle;
import com.scalex.model.User;
import com.scalex.repository.MotorcycleRepository;
import com.scalex.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ScaleXApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaleXApplication.class, args);
    }
    
    @Bean
    @Order(1)
    public CommandLineRunner createTestUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            System.out.println("⏳ Ejecutando CommandLineRunner de Usuario...");
            if (!userRepository.existsByUsername("admin")) {
                User user = new User();
                user.setUsername("admin");
                user.setEmail("admin@test.com");
                user.setPassword(passwordEncoder.encode("admin123"));
                userRepository.save(user);
                System.out.println("✅ Usuario de prueba creado: admin / admin123");
            } else {
                System.out.println("ℹ️ Usuario admin ya existe");
            }
        };
    }
    
    @Bean
    @Order(2)
    public CommandLineRunner createTestMotorcycles(MotorcycleRepository motorcycleRepository) {
        return args -> {
            System.out.println("⏳ Ejecutando CommandLineRunner de Motos...");
            System.out.println("📊 Motos actuales en BD: " + motorcycleRepository.count());
            
            if (motorcycleRepository.count() == 0) {
                System.out.println("🏍️ Creando motos de prueba...");
                
                try {
                    // Motos deportivas
                    Motorcycle m1 = new Motorcycle();
                    m1.setMake("Yamaha");
                    m1.setModel("YZF-R1");
                    m1.setYear(2023);
                    m1.setCategory("Deportiva");
                    m1.setEngineSize(998);
                    m1.setPower(200);
                    m1.setImageUrl("https://via.placeholder.com/400x300/1e3c72/ffffff?text=Yamaha+YZF-R1");
                    motorcycleRepository.save(m1);
                    System.out.println("✅ Moto 1 creada: " + m1.getMake() + " " + m1.getModel());
                    
                    Motorcycle m2 = new Motorcycle();
                    m2.setMake("Honda");
                    m2.setModel("CBR1000RR");
                    m2.setYear(2023);
                    m2.setCategory("Deportiva");
                    m2.setEngineSize(999);
                    m2.setPower(189);
                    m2.setImageUrl("https://via.placeholder.com/400x300/dc2626/ffffff?text=Honda+CBR1000RR");
                    motorcycleRepository.save(m2);
                    System.out.println("✅ Moto 2 creada: " + m2.getMake() + " " + m2.getModel());
                    
                    Motorcycle m3 = new Motorcycle();
                    m3.setMake("Kawasaki");
                    m3.setModel("Ninja ZX-10R");
                    m3.setYear(2023);
                    m3.setCategory("Deportiva");
                    m3.setEngineSize(998);
                    m3.setPower(203);
                    m3.setImageUrl("https://via.placeholder.com/400x300/16a34a/ffffff?text=Kawasaki+ZX-10R");
                    motorcycleRepository.save(m3);
                    System.out.println("✅ Moto 3 creada: " + m3.getMake() + " " + m3.getModel());
                    
                    Motorcycle m4 = new Motorcycle();
                    m4.setMake("Suzuki");
                    m4.setModel("GSX-R1000");
                    m4.setYear(2023);
                    m4.setCategory("Deportiva");
                    m4.setEngineSize(999);
                    m4.setPower(199);
                    m4.setImageUrl("https://via.placeholder.com/400x300/3b82f6/ffffff?text=Suzuki+GSXR1000");
                    motorcycleRepository.save(m4);
                    System.out.println("✅ Moto 4 creada: " + m4.getMake() + " " + m4.getModel());
                    
                    Motorcycle m5 = new Motorcycle();
                    m5.setMake("Ducati");
                    m5.setModel("Panigale V4");
                    m5.setYear(2023);
                    m5.setCategory("Deportiva");
                    m5.setEngineSize(1103);
                    m5.setPower(214);
                    m5.setImageUrl("https://via.placeholder.com/400x300/dc2626/ffffff?text=Ducati+Panigale+V4");
                    motorcycleRepository.save(m5);
                    System.out.println("✅ Moto 5 creada: " + m5.getMake() + " " + m5.getModel());
                    
                    // Motos Naked
                    Motorcycle m6 = new Motorcycle();
                    m6.setMake("Yamaha");
                    m6.setModel("MT-09");
                    m6.setYear(2023);
                    m6.setCategory("Naked");
                    m6.setEngineSize(889);
                    m6.setPower(117);
                    m6.setImageUrl("https://via.placeholder.com/400x300/2a5298/ffffff?text=Yamaha+MT-09");
                    motorcycleRepository.save(m6);
                    System.out.println("✅ Moto 6 creada: " + m6.getMake() + " " + m6.getModel());
                    
                    Motorcycle m7 = new Motorcycle();
                    m7.setMake("Kawasaki");
                    m7.setModel("Z900");
                    m7.setYear(2023);
                    m7.setCategory("Naked");
                    m7.setEngineSize(948);
                    m7.setPower(125);
                    m7.setImageUrl("https://via.placeholder.com/400x300/16a34a/ffffff?text=Kawasaki+Z900");
                    motorcycleRepository.save(m7);
                    System.out.println("✅ Moto 7 creada: " + m7.getMake() + " " + m7.getModel());
                    
                    Motorcycle m8 = new Motorcycle();
                    m8.setMake("KTM");
                    m8.setModel("890 Duke");
                    m8.setYear(2023);
                    m8.setCategory("Naked");
                    m8.setEngineSize(890);
                    m8.setPower(115);
                    m8.setImageUrl("https://via.placeholder.com/400x300/f97316/ffffff?text=KTM+890+Duke");
                    motorcycleRepository.save(m8);
                    System.out.println("✅ Moto 8 creada: " + m8.getMake() + " " + m8.getModel());
                    
                    // Motos Adventure
                    Motorcycle m9 = new Motorcycle();
                    m9.setMake("BMW");
                    m9.setModel("R 1250 GS");
                    m9.setYear(2023);
                    m9.setCategory("Adventure");
                    m9.setEngineSize(1254);
                    m9.setPower(136);
                    m9.setImageUrl("https://via.placeholder.com/400x300/3b82f6/ffffff?text=BMW+R1250GS");
                    motorcycleRepository.save(m9);
                    System.out.println("✅ Moto 9 creada: " + m9.getMake() + " " + m9.getModel());
                    
                    Motorcycle m10 = new Motorcycle();
                    m10.setMake("Honda");
                    m10.setModel("Africa Twin");
                    m10.setYear(2023);
                    m10.setCategory("Adventure");
                    m10.setEngineSize(1084);
                    m10.setPower(102);
                    m10.setImageUrl("https://via.placeholder.com/400x300/dc2626/ffffff?text=Africa+Twin");
                    motorcycleRepository.save(m10);
                    System.out.println("✅ Moto 10 creada: " + m10.getMake() + " " + m10.getModel());
                    
                    System.out.println("🎉 TOTAL: " + motorcycleRepository.count() + " motos creadas exitosamente");
                    
                } catch (Exception e) {
                    System.err.println("❌ Error al crear motos: " + e.getMessage());
                    e.printStackTrace();
                }
                
            } else {
                System.out.println("ℹ️ Ya existen " + motorcycleRepository.count() + " motos en la BD");
            }
        };
    }
}
