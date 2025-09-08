package com.hotel.gateway.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Booking {
    private Long id;
    private Long guestId;
    private Long roomId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Room room; // For GraphQL relationships

    // Default constructor
    public Booking() {}

    // Constructor with all fields
    public Booking(Long id, Long guestId, Long roomId, LocalDateTime checkInDate,
                   LocalDateTime checkOutDate, BigDecimal totalAmount, String status,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGuestId() { return guestId; }
    public void setGuestId(Long guestId) { this.guestId = guestId; }

    public Long getRoomId() { return roomId; }
    public void setRoomId(Long roomId) { this.roomId = roomId; }

    public LocalDateTime getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDateTime checkInDate) { this.checkInDate = checkInDate; }

    public LocalDateTime getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDateTime checkOutDate) { this.checkOutDate = checkOutDate; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
}

