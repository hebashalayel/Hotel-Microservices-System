package com.hotel.booking.service;

import com.hotel.booking.entity.Booking;
import com.hotel.booking.grpc.RoomGrpcClient;
import com.hotel.booking.repository.BookingRepository;
import com.hotel.grpc.room.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoomGrpcClient roomGrpcClient;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Map<String, Object> getBookingWithRoomDetails(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            
            // Call room-service to get room details using REST
            Object room = restTemplate.getForObject("http://room-service/rooms/" + booking.getRoomId(), Object.class);
            
            Map<String, Object> response = new HashMap<>();
            response.put("booking", booking);
            response.put("room", room);
            
            return response;
        }
        return null;
    }

    public Map<String, Object> getBookingWithRoomDetailsViaGrpc(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            
            // Call room-service to get room details using gRPC
            RoomResponse roomResponse = roomGrpcClient.getRoomById(booking.getRoomId());
            
            // Convert gRPC response to a map for consistency
            Map<String, Object> roomMap = new HashMap<>();
            roomMap.put("id", roomResponse.getId());
            roomMap.put("room_number", roomResponse.getRoomNumber());
            roomMap.put("room_type", roomResponse.getRoomType());
            roomMap.put("price_per_night", roomResponse.getPricePerNight());
            roomMap.put("status", roomResponse.getStatus());
            roomMap.put("floor_number", roomResponse.getFloorNumber());
            roomMap.put("max_occupancy", roomResponse.getMaxOccupancy());
            roomMap.put("description", roomResponse.getDescription());
            roomMap.put("created_at", roomResponse.getCreatedAt());
            roomMap.put("updated_at", roomResponse.getUpdatedAt());
            
            Map<String, Object> response = new HashMap<>();
            response.put("booking", booking);
            response.put("room", roomMap);
            response.put("communication_type", "gRPC");
            
            return response;
        }
        return null;
    }
}

