package com.myapp.hotel.service;

import com.myapp.hotel.dto.RoomRequest;
import com.myapp.hotel.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    public Boolean addRoom(RoomRequest roomRequest);
    public RoomRequest convertToDto(Room room);
    public Optional<Room> findRoomById(Long id);
    List<Room> findAllRoom();
    List<Room> findByNoOfOccupants(int value);
}
