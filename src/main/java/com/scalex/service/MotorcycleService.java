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

@Service
public class MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    public MotorcycleService(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<Motorcycle> findAll() {
        return motorcycleRepository.findAll();
    }

    public Optional<Motorcycle> findById(Long id) {
        return motorcycleRepository.findById(id);
    }

    public List<Motorcycle> searchMotorcycles(String make, String model, String category) {
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
        MotorcycleDTO dto = new MotorcycleDTO();
        dto.setId(motorcycle.getId());
        dto.setMake(motorcycle.getMake());
        dto.setModel(motorcycle.getModel());
        dto.setYear(motorcycle.getYear());
        dto.setCategory(motorcycle.getCategory());
        dto.setEngineSize(motorcycle.getEngineSize());
        dto.setPower(motorcycle.getPower());
        dto.setTorque(motorcycle.getTorque());
        dto.setWeight(motorcycle.getWeight());
        dto.setSeatHeight(motorcycle.getSeatHeight());
        dto.setFuelCapacity(motorcycle.getFuelCapacity());
        dto.setImageUrl(motorcycle.getImageUrl());
        dto.setLength(motorcycle.getLength());
        dto.setWidth(motorcycle.getWidth());
        dto.setHeight(motorcycle.getHeight());
        dto.setFavorite(false);
        return dto;
    }

    public MotorcycleDTO convertToDTOWithBuilder(Motorcycle motorcycle) {
        return MotorcycleDTO.builder()
                .id(motorcycle.getId())
                .make(motorcycle.getMake())
                .model(motorcycle.getModel())
                .year(motorcycle.getYear())
                .category(motorcycle.getCategory())
                .engineSize(motorcycle.getEngineSize())
                .power(motorcycle.getPower())
                .torque(motorcycle.getTorque())
                .weight(motorcycle.getWeight())
                .seatHeight(motorcycle.getSeatHeight())
                .fuelCapacity(motorcycle.getFuelCapacity())
                .imageUrl(motorcycle.getImageUrl())
                .length(motorcycle.getLength())
                .width(motorcycle.getWidth())
                .height(motorcycle.getHeight())
                .isFavorite(false)
                .build();
    }
}