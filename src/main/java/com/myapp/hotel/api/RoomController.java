package com.myapp.hotel.api;


import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.RoomRequest;
import com.myapp.hotel.model.Room;
import com.myapp.hotel.service.RoomService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/room")
@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping("/save")
    public Boolean addRoom(@Valid @NonNull @RequestBody RoomRequest roomRequest){
        Boolean isSaved = roomService.addRoom(roomRequest);
        return isSaved;
    }
    @GetMapping("/retrieveall")
    public List<BaseModel> getAllRoom(){
        return roomService.findAllRoom();
    }

    @GetMapping("retrieve/{id}")
    public Optional<Room> getRoomById(@PathVariable{id} Long id){
        return roomService.findRoomById(id);
    }
}
