package com.hotel.booking.controller;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookingWithRoomDetails(@PathVariable Long id) {
        Map<String, Object> bookingWithRoom = bookingService.getBookingWithRoomDetails(id);
        if (bookingWithRoom != null) {
            return ResponseEntity.ok(bookingWithRoom);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/grpc")
    public ResponseEntity<Map<String, Object>> getBookingWithRoomDetailsViaGrpc(@PathVariable Long id) {
        Map<String, Object> bookingWithRoom = bookingService.getBookingWithRoomDetailsViaGrpc(id);
        if (bookingWithRoom != null) {
            return ResponseEntity.ok(bookingWithRoom);
        }
        return ResponseEntity.notFound().build();
    }
}

