package com.myapp.hotel.dto;

import lombok.Data;


import javax.tools.StandardLocation;
import javax.validation.constraints.NotNull;

@Data
public class CustomerRequest extends BaseModel {
    public StandardLocation customer;
    private  Long id;
    @NotNull
    private  String name;
    @NotNull
    private String email;
    @NotNull
    private  String phoneNo;
    private  String address;
}
