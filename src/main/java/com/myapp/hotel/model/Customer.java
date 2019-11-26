package com.myapp.hotel.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @NotNull
    private  String name;
    @NotNull
    private  String email;
    @NotNull
    private  String phoneNo;
    private  String address;


    public Customer() {
    }


}
