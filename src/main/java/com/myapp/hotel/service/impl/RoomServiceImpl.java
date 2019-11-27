package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.RoomRequest;
import com.myapp.hotel.model.Room;
import com.myapp.hotel.repository.RoomRepository;
import com.myapp.hotel.service.RoomService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private final RoomRepository roomRepository;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(RoomServiceImpl.class));

    public RoomServiceImpl(RoomRepository roomRepository, Mapper mapper) {
        this.roomRepository = roomRepository;
        this.mapper = mapper;
    }

    @Override
    public Boolean addRoom(RoomRequest roomRequest) {
        Room room = mapper.map(roomRequest, Room.class);
        boolean saved = false;
        try{
            roomRepository.save(room);
            saved = true;
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("failed");
        }

        return saved;
    }

    @Override
    public Optional<Room> findRoomById(Long id) {
        try{
            roomRepository.findById(id);
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("Not found");
        }
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> findAllRoom() {
        try{
            roomRepository.findAll();
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("Not found");
        }
        return roomRepository.findAll();
    }
}
