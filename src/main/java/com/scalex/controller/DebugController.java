package com.scalex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class DebugController {

    @Autowired
    private Environment env;
    
    @Autowired
    private DataSource dataSource;

    @GetMapping("/debug/config")
    public String debugConfig() {
        StringBuilder sb = new StringBuilder();
        sb.append("Active Profile: ").append(String.join(",", env.getActiveProfiles())).append("\n");
        sb.append("DATABASE_URL exists: ").append(env.getProperty("DATABASE_URL") != null).append("\n");
        sb.append("DataSource class: ").append(dataSource.getClass().getName()).append("\n");
        
        try (Connection conn = dataSource.getConnection()) {
            sb.append("Database Product: ").append(conn.getMetaData().getDatabaseProductName()).append("\n");
            sb.append("Database Version: ").append(conn.getMetaData().getDatabaseProductVersion()).append("\n");
        } catch (Exception e) {
            sb.append("Error: ").append(e.getMessage()).append("\n");
        }
        
        return sb.toString();
    }
}