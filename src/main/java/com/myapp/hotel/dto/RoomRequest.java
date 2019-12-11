package com.myapp.hotel.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class RoomRequest extends BaseModel{
    private Long id;
    @NotNull
    private String roomNumber;
    @NotNull
    private Long categoryAmount;
    @NotNull
    private String categoryName;
    @NotNull
    private String roomFloor;
}