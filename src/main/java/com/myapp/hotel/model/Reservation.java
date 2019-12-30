package com.myapp.hotel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "customer")

@Entity
public class Reservation {

    @OneToOne
    @JoinColumn
    private Customer customer;
    @JoinColumn
    @OneToMany
    private Set<Room> room;

    @OneToOne
    private Payment payment;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Date checkIn;
    private Date checkOut;
    private String referenceNo;

}
