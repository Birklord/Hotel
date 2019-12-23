package com.myapp.hotel.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @NotNull
    private  String firstName;
    @NotNull
    private String LastName;
    @NotNull
    private  String email;
    @NotNull
    private  String phone;
    private  String address;
    @NotNull
    private String customerCode;



    public Customer() {
    }


}
