package com.scalex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/health")
    public String health() {
        try {
            // Verificar conexión a la base de datos
            jdbcTemplate.execute("SELECT 1");
            return "OK - Database connected";
        } catch (Exception e) {
            return "ERROR - " + e.getMessage();
        }
    }
    
    @GetMapping("/debug")
    public String debug() {
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Scalex Debug</h1>");
        sb.append("<p>Application is running</p>");
        
        // Verificar variables de entorno
        sb.append("<h2>Environment Variables</h2>");
        sb.append("<p>DATABASE_URL: ").append(System.getenv("DATABASE_URL")).append("</p>");
        sb.append("<p>PORT: ").append(System.getenv("PORT")).append("</p>");
        
        return sb.toString();
    }
}