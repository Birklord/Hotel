package com.myapp.hotel.service;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.RoomRequest;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    public Boolean addRoom(RoomRequest roomRequest);
    public Optional<BaseModel> findRoomById(Long id);
    List<BaseModel> findAllRoom();
}
