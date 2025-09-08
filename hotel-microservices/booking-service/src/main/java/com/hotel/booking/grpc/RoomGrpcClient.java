package com.hotel.booking.grpc;

import com.hotel.grpc.room.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class RoomGrpcClient {

    @GrpcClient("room-service")
    private RoomServiceGrpc.RoomServiceBlockingStub roomServiceStub;

    public RoomResponse getRoomById(Long roomId) {
        GetRoomRequest request = GetRoomRequest.newBuilder()
                .setId(roomId)
                .build();
        
        return roomServiceStub.getRoom(request);
    }

    public GetAllRoomsResponse getAllRooms() {
        GetAllRoomsRequest request = GetAllRoomsRequest.newBuilder().build();
        return roomServiceStub.getAllRooms(request);
    }
}

