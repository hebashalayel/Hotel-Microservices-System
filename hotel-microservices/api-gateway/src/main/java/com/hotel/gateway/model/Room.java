package com.hotel.gateway.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Room {
    private Long id;
    private String roomNumber;
    private String roomType;
    private BigDecimal pricePerNight;
    private String status;
    private Integer floorNumber;
    private Integer maxOccupancy;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Default constructor
    public Room() {}

    // Constructor with all fields
    public Room(Long id, String roomNumber, String roomType, BigDecimal pricePerNight, 
                String status, Integer floorNumber, Integer maxOccupancy, String description,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.status = status;
        this.floorNumber = floorNumber;
        this.maxOccupancy = maxOccupancy;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public BigDecimal getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(BigDecimal pricePerNight) { this.pricePerNight = pricePerNight; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getFloorNumber() { return floorNumber; }
    public void setFloorNumber(Integer floorNumber) { this.floorNumber = floorNumber; }

    public Integer getMaxOccupancy() { return maxOccupancy; }
    public void setMaxOccupancy(Integer maxOccupancy) { this.maxOccupancy = maxOccupancy; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

