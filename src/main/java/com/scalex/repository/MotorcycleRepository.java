package com.scalex.repository;

import com.scalex.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long>, JpaSpecificationExecutor<Motorcycle> {
    List<Motorcycle> findByMakeContainingIgnoreCase(String make);
    List<Motorcycle> findByCategoryContainingIgnoreCase(String category);
    List<Motorcycle> findByMakeAndModelContainingIgnoreCase(String make, String model);
}