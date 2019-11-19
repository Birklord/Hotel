package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Room {

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String number;
    private String status;



}
