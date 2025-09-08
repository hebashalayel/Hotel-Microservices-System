package com.hotel.room.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String roomNumber;
    
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
    private BigDecimal pricePerNight;
    
    @Enumerated(EnumType.STRING)
    private RoomStatus status;
    
    private Integer floorNumber;
    private Integer maxOccupancy;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    public enum RoomType {
        SINGLE, DOUBLE, SUITE, DELUXE, PRESIDENTIAL
    }
    
    public enum RoomStatus {
        AVAILABLE, OCCUPIED, MAINTENANCE, OUT_OF_ORDER, CLEANING
    }
}

