package com.hotel.gateway.graphql;

import com.hotel.gateway.model.Booking;
import com.hotel.gateway.model.Room;
import com.hotel.gateway.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class HotelGraphQLController {

    @Autowired
    private HotelService hotelService;

    @QueryMapping
    public List<Room> rooms() {
        return hotelService.getAllRooms();
    }

    @QueryMapping
    public Room room(@Argument Long id) {
        return hotelService.getRoomById(id);
    }

    @QueryMapping
    public List<Booking> bookings() {
        return hotelService.getAllBookings();
    }

    @QueryMapping
    public Booking booking(@Argument Long id) {
        return hotelService.getBookingById(id);
    }

    @SchemaMapping
    public Room room(Booking booking) {
        if (booking.getRoom() != null) {
            return booking.getRoom();
        }
        return hotelService.getRoomById(booking.getRoomId());
    }
}

