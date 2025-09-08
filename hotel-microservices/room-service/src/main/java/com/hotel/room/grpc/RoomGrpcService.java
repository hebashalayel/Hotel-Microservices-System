package com.hotel.room.grpc;

import com.hotel.grpc.room.*;
import com.hotel.room.entity.Room;
import com.hotel.room.service.RoomService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@GrpcService
public class RoomGrpcService extends RoomServiceGrpc.RoomServiceImplBase {

    @Autowired
    private RoomService roomService;

    @Override
    public void getRoom(GetRoomRequest request, StreamObserver<RoomResponse> responseObserver) {
        Optional<Room> roomOptional = roomService.getRoomById(request.getId());
        
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            RoomResponse response = RoomResponse.newBuilder()
                    .setId(room.getId())
                    .setRoomNumber(room.getRoomNumber())
                    .setRoomType(room.getRoomType().toString())
                    .setPricePerNight(room.getPricePerNight().doubleValue())
                    .setStatus(room.getStatus().toString())
                    .setFloorNumber(room.getFloorNumber())
                    .setMaxOccupancy(room.getMaxOccupancy())
                    .setDescription(room.getDescription() != null ? room.getDescription() : "")
                    .setCreatedAt(room.getCreatedAt().toString())
                    .setUpdatedAt(room.getUpdatedAt().toString())
                    .build();
            
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Room not found with id: " + request.getId()));
        }
    }

    @Override
    public void getAllRooms(GetAllRoomsRequest request, StreamObserver<GetAllRoomsResponse> responseObserver) {
        List<Room> rooms = roomService.getAllRooms();
        
        GetAllRoomsResponse.Builder responseBuilder = GetAllRoomsResponse.newBuilder();
        
        for (Room room : rooms) {
            RoomResponse roomResponse = RoomResponse.newBuilder()
                    .setId(room.getId())
                    .setRoomNumber(room.getRoomNumber())
                    .setRoomType(room.getRoomType().toString())
                    .setPricePerNight(room.getPricePerNight().doubleValue())
                    .setStatus(room.getStatus().toString())
                    .setFloorNumber(room.getFloorNumber())
                    .setMaxOccupancy(room.getMaxOccupancy())
                    .setDescription(room.getDescription() != null ? room.getDescription() : "")
                    .setCreatedAt(room.getCreatedAt().toString())
                    .setUpdatedAt(room.getUpdatedAt().toString())
                    .build();
            
            responseBuilder.addRooms(roomResponse);
        }
        
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}

