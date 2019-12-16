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
            if(value == 0)
                return null;
            if((value == 1)||(value == 2)) {
                value = 2;
//                Pageable pageable = (Pageable) PageRequest.of(page, size);
                return roomRepository.occupantCheck(pageable);
            }
            else if(value>=2){
             List<Room> roomSuggest = roomRepository.occupantAnalyzer();
                List<Room> roomReturns = new ArrayList<>();
                int count = 0;
                for(int i = 0; i< roomSuggest.size(); i++){
                    do {
                        if (roomReturns.get(i).getMaximum() >= value) {
                            roomReturns.add(roomReturns.get(i));
                            count += 7;
                        }
                    }while (count <= value);
                     break;
                    }
                return roomReturns;
                }

//                List<Room> suggest = roomRepository.suggest(value, pageable);
//                for(Room room : suggest){
//                    if(room.getMaximum()>7){
//                        list.add(room);
//                    }
//                }
//                return suggest;
//            }
//        return roomRepository.findAll();
//        int count = 0;
//        for(int i = 1; i<= value;  i++){
//            if(value == 2){
//            roomRepository.occupantCheck(pageable);
//            count  += 2;
//            return roomRepository.occupantCheck(pageable);
//        }
//        else (value >= 5){
//                int count = 0;
//            for(int I = value); i<= count; i++){

            return roomRepository.occupantAnalyzer();
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
