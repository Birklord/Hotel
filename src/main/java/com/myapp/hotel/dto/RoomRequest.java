package com.myapp.hotel.dto;


import javax.validation.constraints.NotNull;

public class RoomRequest {
    private Long id;
    @NotNull
    private String roomNumber;
    @NotNull
    private Long categoryAmount;
    @NotNull
    private String categoryName;
}