package com.scalex.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL:}")
    private String databaseUrl;

    @Bean
    public DataSource dataSource() {
        // Si DATABASE_URL existe, usar PostgreSQL
        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            System.out.println("🐘 Configurando PostgreSQL...");
            System.out.println("📊 Database URL detectada: " + maskPassword(databaseUrl));
            
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(databaseUrl);
            config.setDriverClassName("org.postgresql.Driver");
            config.setMaximumPoolSize(5);
            
            return new HikariDataSource(config);
        }
        
        // Si no, usar H2 (desarrollo local)
        System.out.println("💾 Usando H2 en memoria (desarrollo local)");
        return null; // Spring Boot usará la configuración por defecto
    }
    
    private String maskPassword(String url) {
        // Ocultar la contraseña en los logs
        if (url.contains("@")) {
            String[] parts = url.split("@");
            String credentials = parts[0].substring(parts[0].lastIndexOf("//") + 2);
            String[] credParts = credentials.split(":");
            return url.replace(credentials, credParts[0] + ":****");
        }
        return url;
    }
}