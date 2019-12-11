package com.myapp.hotel.api;


import com.myapp.hotel.dto.ResponseModel;
import com.myapp.hotel.dto.RoomRequest;
import com.myapp.hotel.model.Room;
import com.myapp.hotel.service.RoomService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RequestMapping("api/v1/room")
@RestController
public class RoomController {
    private final RoomService roomService;
    static Logger logger = Logger.getLogger(String.valueOf(RoomController.class));
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/save")
    public Boolean addRoom(@Valid @NonNull @RequestBody RoomRequest roomRequest){
        Boolean isSaved = false;
        try {
            roomService.addRoom(roomRequest);
            isSaved=true;
            return isSaved;
        }catch(Exception e){
            e.printStackTrace();
            isSaved=false;
            logger.severe("Failed");
        }
        return isSaved;
    }
    @GetMapping("/roomsearch/{value}")
    public ResponseModel determineRoomByNoOfOccupants(@PathVariable int value){
        ResponseModel responseModel = new ResponseModel();
//        try {
            List<Room> roomList = roomService.findByNoOfOccupants(value);

                responseModel.setInfo(roomList);
                responseModel.setResponseCode("00");
                responseModel.setValid(true);
                responseModel.setResponseMessage("Success fetching customers");
//        }
//        catch(Exception e) {
//            e.printStackTrace();
//            e.getMessage();
//            responseModel.setData(null);
//            responseModel.setResponseCode("99");
//            responseModel.setValid(false);
//            responseModel.setResponseMessage("Failure fetching customers");
//            logger.severe("Failure fetching customers");
//        }

        return responseModel;
    }

    @GetMapping("/retrieveall")
    public List<Room> getAllRoom(){
        return roomService.findAllRoom();
    }

    @GetMapping("/retrieve/{id}")
    public Optional<Room> getRoomById(@PathVariable Long id){
         return roomService.findRoomById(id);
    }
}
