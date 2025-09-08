package com.hotel.gateway.service;

import com.hotel.gateway.model.Booking;
import com.hotel.gateway.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String ROOM_SERVICE_URL = "http://room-service";
    private static final String BOOKING_SERVICE_URL = "http://booking-service";

    public List<Room> getAllRooms() {
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                ROOM_SERVICE_URL + "/rooms",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );

        return response.getBody().stream()
                .map(this::mapToRoom)
                .collect(Collectors.toList());
    }

    public Room getRoomById(Long id) {
        Map<String, Object> roomData = restTemplate.getForObject(
                ROOM_SERVICE_URL + "/rooms/" + id,
                Map.class
        );
        return mapToRoom(roomData);
    }

    public List<Booking> getAllBookings() {
        ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                BOOKING_SERVICE_URL + "/bookings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );

        return response.getBody().stream()
                .map(this::mapToBooking)
                .collect(Collectors.toList());
    }

    public Booking getBookingById(Long id) {
        Map<String, Object> bookingData = restTemplate.getForObject(
                BOOKING_SERVICE_URL + "/bookings/" + id,
                Map.class
        );
        
        if (bookingData != null && bookingData.containsKey("booking")) {
            Map<String, Object> booking = (Map<String, Object>) bookingData.get("booking");
            Map<String, Object> room = (Map<String, Object>) bookingData.get("room");
            
            Booking result = mapToBooking(booking);
            if (room != null) {
                result.setRoom(mapToRoom(room));
            }
            return result;
        }
        return null;
    }

    private Room mapToRoom(Map<String, Object> data) {
        if (data == null) return null;
        
        Room room = new Room();
        room.setId(((Number) data.get("id")).longValue());
        room.setRoomNumber((String) data.get("room_number"));
        room.setRoomType((String) data.get("room_type"));
        room.setPricePerNight(new BigDecimal(data.get("price_per_night").toString()));
        room.setStatus((String) data.get("status"));
        room.setFloorNumber((Integer) data.get("floor_number"));
        room.setMaxOccupancy((Integer) data.get("max_occupancy"));
        room.setDescription((String) data.get("description"));
        
        if (data.get("created_at") != null) {
            room.setCreatedAt(LocalDateTime.parse((String) data.get("created_at")));
        }
        if (data.get("updated_at") != null) {
            room.setUpdatedAt(LocalDateTime.parse((String) data.get("updated_at")));
        }
        
        return room;
    }

    private Booking mapToBooking(Map<String, Object> data) {
        if (data == null) return null;
        
        Booking booking = new Booking();
        booking.setId(((Number) data.get("id")).longValue());
        booking.setGuestId(((Number) data.get("guest_id")).longValue());
        booking.setRoomId(((Number) data.get("room_id")).longValue());
        booking.setTotalAmount(new BigDecimal(data.get("total_amount").toString()));
        booking.setStatus((String) data.get("status"));
        
        if (data.get("check_in_date") != null) {
            booking.setCheckInDate(LocalDateTime.parse((String) data.get("check_in_date")));
        }
        if (data.get("check_out_date") != null) {
            booking.setCheckOutDate(LocalDateTime.parse((String) data.get("check_out_date")));
        }
        if (data.get("created_at") != null) {
            booking.setCreatedAt(LocalDateTime.parse((String) data.get("created_at")));
        }
        if (data.get("updated_at") != null) {
            booking.setUpdatedAt(LocalDateTime.parse((String) data.get("updated_at")));
        }
        
        return booking;
    }
}

