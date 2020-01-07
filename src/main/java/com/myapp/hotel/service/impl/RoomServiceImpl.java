package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.RoomRequest;
import com.myapp.hotel.model.Room;
import com.myapp.hotel.repository.RoomRepository;
import com.myapp.hotel.service.RoomService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public RoomRequest convertToDto(Room room) {
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setCategoryName(room.getRoomType());
        roomRequest.setCategoryAmount(room.getRoomTypeAmount());
        roomRequest.setRoomNumber(room.getRoomNumber());
        roomRequest.setRoomFloor(room.getRoomFloor());
        return roomRequest;
    }

    @Override
    public List<Room> findByNoOfOccupants(int value, int page, int size){
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        int maxRoom=2;
                    if(value <= 2) {
                        return roomRepository.occupantCheck(pageable, maxRoom);
                    }
                    else {
                             List<Room> allAvailableRooms = roomRepository.findAllAvailableRooms();
                                List<Room> roomReturns = new ArrayList<>();
                                int count = 0;
                                for(int i = 0; i< allAvailableRooms.size(); i++){

                                    if (allAvailableRooms.get(i).getMaximum() >= value) {
                                        roomReturns.add(allAvailableRooms.get(i));
                                        count += allAvailableRooms.get(i).getMaximum();
                                    }
                                    if((value-count)<3) {
                                        List<Room> room = roomRepository.occupantCheck(pageable, maxRoom);
                                        roomReturns.add(room.get(i));
                                        count += room.get(i).getMaximum();
                                        break;
                                    }
                                    else if(count >= value){
                                        break;
                                    }
                                }
                                return roomReturns;
                    }

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
