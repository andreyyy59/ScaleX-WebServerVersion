package com.scalex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "motorcycles")
public class Motorcycle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String make;
    
    @NotBlank
    private String model;
    
    @Column(name = "\"year\"")  // ← ESCAPA la columna year
    private Integer year;
    
    private String category;
    
    @DecimalMin("0.0")
    @Column(name = "engine_size")
    private int engineSize;
    
    @DecimalMin("0.0")
    private int power;
    
    @DecimalMin("0.0")
    private BigDecimal torque;
    
    @DecimalMin("0.0")
    private BigDecimal weight;
    
    @DecimalMin("0.0")
    @Column(name = "seat_height")
    private BigDecimal seatHeight;
    
    @DecimalMin("0.0")
    @Column(name = "fuel_capacity")
    private BigDecimal fuelCapacity;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @DecimalMin("0.0")
    private BigDecimal length;
    
    @DecimalMin("0.0")
    private BigDecimal width;
    
    @DecimalMin("0.0")
    private BigDecimal height;
    
    // Constructors
    public Motorcycle() {
    }
    
    public Motorcycle(String make, String model, Integer year, String category) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.category = category;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getEngineSize() {
        return engineSize;
    }
    
    public void setEngineSize(int i) {
        this.engineSize = i;
    }
    
    public int getPower() {
        return power;
    }
    
    public void setPower(int i) {
        this.power = i;
    }
    
    public BigDecimal getTorque() {
        return torque;
    }
    
    public void setTorque(BigDecimal torque) {
        this.torque = torque;
    }
    
    public BigDecimal getWeight() {
        return weight;
    }
    
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    public BigDecimal getSeatHeight() {
        return seatHeight;
    }
    
    public void setSeatHeight(BigDecimal seatHeight) {
        this.seatHeight = seatHeight;
    }
    
    public BigDecimal getFuelCapacity() {
        return fuelCapacity;
    }
    
    public void setFuelCapacity(BigDecimal fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public BigDecimal getLength() {
        return length;
    }
    
    public void setLength(BigDecimal length) {
        this.length = length;
    }
    
    public BigDecimal getWidth() {
        return width;
    }
    
    public void setWidth(BigDecimal width) {
        this.width = width;
    }
    
    public BigDecimal getHeight() {
        return height;
    }
    
    public void setHeight(BigDecimal height) {
        this.height = height;
    }
    
    @Override
    public String toString() {
        return "Motorcycle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                '}';
    }
}