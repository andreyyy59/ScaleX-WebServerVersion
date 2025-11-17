package com.scalex.dto;

import java.math.BigDecimal;

public class MotorcycleDTO {
    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String category;
    private Integer engineSize;  // Cambiado a Integer para coincidir con la entidad
    private Integer power;       // Cambiado a Integer para coincidir con la entidad
    private String torque;
    private BigDecimal weight;
    private BigDecimal seatHeight;
    private BigDecimal fuelCapacity;
    private String imageUrl;
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;
    private boolean isFavorite;
    
    // Constructores
    public MotorcycleDTO() {
    }
    
    public MotorcycleDTO(Long id, String make, String model, Integer year, String category) {
        this.id = id;
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
    
    public Integer getEngineSize() {
        return engineSize;
    }
    
    
    public Integer getPower() {
        return power;
    }
    
    public void setPower(Integer power) {
        this.power = power;
    }
    
    public String getTorque() {
        return torque;
    }
    
    public void setTorque(String torque) {
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
    
    public boolean isFavorite() {
        return isFavorite;
    }
    
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    
    // Helper method para el getter (alternativo para Thymeleaf)
    public boolean getIsFavorite() {
        return isFavorite;
    }
    
    public void setIsFavorite(boolean favorite) {
        isFavorite = favorite;
    }
    
    // toString
    @Override
    public String toString() {
        return "MotorcycleDTO{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                ", engineSize=" + engineSize +
                ", power=" + power +
                ", torque='" + torque + '\'' +
                ", weight=" + weight +
                ", seatHeight=" + seatHeight +
                ", fuelCapacity=" + fuelCapacity +
                ", isFavorite=" + isFavorite +
                '}';
    }
    
    // Builder pattern manual
    public static class Builder {
        private Long id;
        private String make;
        private String model;
        private Integer year;
        private String category;
        private int engineSize;
        private Integer power;
        private String torque;
        private BigDecimal weight;
        private BigDecimal seatHeight;
        private BigDecimal fuelCapacity;
        private String imageUrl;
        private BigDecimal length;
        private BigDecimal width;
        private BigDecimal height;
        private boolean isFavorite;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder make(String make) {
            this.make = make;
            return this;
        }
        
        public Builder model(String model) {
            this.model = model;
            return this;
        }
        
        public Builder year(Integer year) {
            this.year = year;
            return this;
        }
        
        public Builder category(String category) {
            this.category = category;
            return this;
        }
        
        public Builder engineSize(int i) {
            this.engineSize = i;
            return this;
        }
        
        public Builder power(Integer power) {
            this.power = power;
            return this;
        }
        
        public Builder torque(String torque) {
            this.torque = torque;
            return this;
        }
        
        public Builder weight(BigDecimal weight) {
            this.weight = weight;
            return this;
        }
        
        public Builder seatHeight(BigDecimal seatHeight) {
            this.seatHeight = seatHeight;
            return this;
        }
        
        public Builder fuelCapacity(BigDecimal fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
            return this;
        }
        
        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        
        public Builder length(BigDecimal length) {
            this.length = length;
            return this;
        }
        
        public Builder width(BigDecimal width) {
            this.width = width;
            return this;
        }
        
        public Builder height(BigDecimal height) {
            this.height = height;
            return this;
        }
        
        public Builder isFavorite(boolean isFavorite) {
            this.isFavorite = isFavorite;
            return this;
        }
        
        public MotorcycleDTO build() {
            MotorcycleDTO dto = new MotorcycleDTO();
            dto.setId(this.id);
            dto.setMake(this.make);
            dto.setModel(this.model);
            dto.setYear(this.year);
            dto.setCategory(this.category);
            dto.setPower(this.power);
            dto.setTorque(this.torque);
            dto.setWeight(this.weight);
            dto.setSeatHeight(this.seatHeight);
            dto.setFuelCapacity(this.fuelCapacity);
            dto.setImageUrl(this.imageUrl);
            dto.setLength(this.length);
            dto.setWidth(this.width);
            dto.setHeight(this.height);
            dto.setFavorite(this.isFavorite);
            return dto;
        }
    }
    
    // Método estático para crear builder
    public static Builder builder() {
        return new Builder();
    }
}