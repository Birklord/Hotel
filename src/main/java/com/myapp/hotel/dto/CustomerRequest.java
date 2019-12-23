package com.myapp.hotel.dto;

import lombok.Data;


import javax.tools.StandardLocation;
import javax.validation.constraints.NotNull;

@Data
public class CustomerRequest extends BaseModel {

    private  Long id;
//    @NotNull
    private  String firstName;

//    @NotNull
    private  String lastName;
//    @NotNull
    private String email;
//    @NotNull
    private  String phone;
    private  String address;
}
