package com.scalex.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ApiMinjasService {

    @Value("${api.ninjas.key:}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public ApiMinjasService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Método para buscar motos con filtros opcionales
    public String getMotorcycleData(String make, String model, String year) {
        try {
            StringBuilder url = new StringBuilder("https://api.api-ninjas.com/v1/motorcycles");
            
            // Construir parámetros de consulta
            List<String> params = new ArrayList<>();
            if (make != null && !make.isEmpty()) {
                params.add("make=" + make);
            }
            if (model != null && !model.isEmpty()) {
                params.add("model=" + model);
            }
            if (year != null && !year.isEmpty()) {
                params.add("year=" + year);
            }
            
            if (!params.isEmpty()) {
                url.append("?").append(String.join("&", params));
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Api-Key", apiKey);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                url.toString(), HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                System.err.println("API Error: " + response.getStatusCode());
                return "[]";
            }
        } catch (Exception e) {
            System.err.println("Error calling API: " + e.getMessage());
            return "[]";
        }
    }

    // Método para obtener TODAS las motos (haciendo múltiples búsquedas)
    public String getAllMotorcycles() {
        System.out.println("=== API CALL INITIATED ===");
        System.out.println("API Key: " + (apiKey != null && !apiKey.isEmpty() ? "PRESENT" : "MISSING"));
        
        // Verificar que la API key esté configurada
        if (apiKey == null || apiKey.isEmpty() || apiKey.equals("tu_api_key_aqui")) {
            System.err.println("ERROR: API Key not configured properly");
            return "[]";
        }
        
        try {
            // Hacer una sola llamada de prueba primero con Honda
            String testUrl = "https://api.api-ninjas.com/v1/motorcycles?make=Honda";
            System.out.println("Calling URL: " + testUrl);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set("X-Api-Key", apiKey);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            System.out.println("Sending request...");
            ResponseEntity<String> response = restTemplate.exchange(
                testUrl, HttpMethod.GET, entity, String.class);
            
            System.out.println("API Response Status: " + response.getStatusCode());
            System.out.println("API Response Body length: " + 
                              (response.getBody() != null ? response.getBody().length() : "null"));
            
            if (response.getBody() != null && response.getBody().length() > 10) {
                System.out.println("API Response Sample: " + response.getBody().substring(0, 
                                  Math.min(100, response.getBody().length())));
            }
            
            return response.getBody();
            
        } catch (org.springframework.web.client.ResourceAccessException e) {
            System.err.println("TIMEOUT ERROR: " + e.getMessage());
            return "[]";
        } catch (Exception e) {
            System.err.println("API CALL FAILED: " + e.getClass().getSimpleName() + ": " + e.getMessage());
            return "[]";
        }
    }
 // En ApiMinjasService - prueba mínima
    public String testSimpleCall() {
        try {
            String url = "https://api.api-ninjas.com/v1/motorcycles?make=Honda&model=CBR";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", apiKey);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            System.out.println("Testing simple API call...");
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            System.out.println("SUCCESS! Status: " + response.getStatusCode());
            return response.getBody();
            
        } catch (Exception e) {
            System.err.println("SIMPLE TEST FAILED: " + e.getMessage());
            return null;
        }
    }
}