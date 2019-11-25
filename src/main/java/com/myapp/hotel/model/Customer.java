package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;
private  String name;
private  String email;
private  String phoneNo;
private  String address;


    public Customer(Long id, Set<Reservation> reservations, String name, String email, String phoneNo, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public Customer(Long id, String name) {
    }
    public Customer() {
    }

    public String getName() {
        return name;
    }
}
