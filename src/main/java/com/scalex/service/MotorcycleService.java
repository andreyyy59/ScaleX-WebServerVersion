package com.scalex.service;

import com.scalex.dto.MotorcycleDTO;
import com.scalex.model.Motorcycle;
import com.scalex.repository.MotorcycleRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    public MotorcycleService(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    // Método findAll simple
    public List<Motorcycle> findAll() {
        return motorcycleRepository.findAll(); // Este método viene de JpaRepository
    }

    // Método que retorna DTOs
    public List<MotorcycleDTO> findAllDTOs() {
        List<Motorcycle> motorcycles = findAll();
        return motorcycles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<Motorcycle> findById(Long id) {
        return motorcycleRepository.findById(id);
    }

    public List<Motorcycle> searchMotorcycles(String make, String model, String category) {
        // Si no hay parámetros, retornar todas las motos
        if ((make == null || make.isEmpty()) && 
            (model == null || model.isEmpty()) && 
            (category == null || category.isEmpty())) {
            return findAll();
        }

        Specification<Motorcycle> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (make != null && !make.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("make")), 
                    "%" + make.toLowerCase() + "%"
                ));
            }
            
            if (model != null && !model.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("model")), 
                    "%" + model.toLowerCase() + "%"
                ));
            }
            
            if (category != null && !category.isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("category")), 
                    "%" + category.toLowerCase() + "%"
                ));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        
        return motorcycleRepository.findAll(spec);
    }

    public List<MotorcycleDTO> searchMotorcyclesDTOs(String make, String model, String category) {
        return searchMotorcycles(make, model, category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<Motorcycle> findByMakeContainingIgnoreCase(String make) {
        return motorcycleRepository.findByMakeContainingIgnoreCase(make);
    }

    public List<Motorcycle> findByCategoryContainingIgnoreCase(String category) {
        return motorcycleRepository.findByCategoryContainingIgnoreCase(category);
    }

    public Motorcycle save(Motorcycle motorcycle) {
        return motorcycleRepository.save(motorcycle);
    }

    public void deleteById(Long id) {
        motorcycleRepository.deleteById(id);
    }

    public MotorcycleDTO convertToDTO(Motorcycle motorcycle) {
        if (motorcycle == null) {
            return null;
        }
        
        return MotorcycleDTO.builder()
                .id(motorcycle.getId())
                .make(motorcycle.getMake())
                .model(motorcycle.getModel())
                .year(motorcycle.getYear())
                .category(motorcycle.getCategory())
                .engineSize(motorcycle.getEngineSize())
                .power(motorcycle.getPower())
                .torque(calculateTorque(motorcycle.getEngineSize()))
                .weight(calculateWeight(motorcycle.getEngineSize()))
                .seatHeight(calculateSeatHeight(motorcycle.getCategory()))
                .fuelCapacity(calculateFuelCapacity(motorcycle.getEngineSize()))
                .imageUrl("/images/motorcycles/" + motorcycle.getImageUrl())
                .length(calculateLength(motorcycle.getCategory()))
                .width(new BigDecimal("700"))
                .height(new BigDecimal("1100"))
                .isFavorite(false)
                .build();
    }

    // Métodos helper para calcular valores
    private String calculateTorque(Integer engineSize) {
        if (engineSize > 1000) return "120 Nm";
        if (engineSize > 600) return "80 Nm";
        if (engineSize > 300) return "50 Nm";
        return "25 Nm";
    }

    private BigDecimal calculateWeight(Integer engineSize) {
        if (engineSize > 1000) return new BigDecimal("220");
        if (engineSize > 600) return new BigDecimal("190");
        if (engineSize > 300) return new BigDecimal("160");
        return new BigDecimal("130");
    }

    private BigDecimal calculateSeatHeight(String category) {
        if (category == null) return new BigDecimal("780");
        switch (category.toLowerCase()) {
            case "adventure": return new BigDecimal("850");
            case "sport": return new BigDecimal("820");
            case "naked": return new BigDecimal("800");
            case "cruiser": return new BigDecimal("700");
            default: return new BigDecimal("780");
        }
    }

    private BigDecimal calculateFuelCapacity(Integer engineSize) {
        if (engineSize > 1000) return new BigDecimal("18");
        if (engineSize > 600) return new BigDecimal("16");
        if (engineSize > 300) return new BigDecimal("12");
        return new BigDecimal("8");
    }

    private BigDecimal calculateLength(String category) {
        if (category == null) return new BigDecimal("2000");
        switch (category.toLowerCase()) {
            case "cruiser": return new BigDecimal("2300");
            case "adventure": return new BigDecimal("2200");
            case "sport": return new BigDecimal("2100");
            default: return new BigDecimal("2000");
        }
    }
    
}
