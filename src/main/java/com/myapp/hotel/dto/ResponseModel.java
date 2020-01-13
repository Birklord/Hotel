package com.myapp.hotel.dto;

import com.myapp.hotel.model.Room;
import lombok.Data;

import java.util.List;
@Data
public class ResponseModel {
    private boolean valid;
    public List<BaseModel> data;
    public List<Room> info;
    private String responseCode;
    private String responseMessage;

}
