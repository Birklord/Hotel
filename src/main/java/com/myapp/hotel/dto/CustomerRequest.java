package com.myapp.hotel.dto;

import lombok.Data;

import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;

@Data
@PersistenceContext
public class CustomerRequest {
    private  Long id;
    @NotNull
    private  String name;
    @NotNull
    private  String email;
    @NotNull
    private  String phoneNo;
    private  String address;
}
