package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
//@EqualsAndHashCode(exclude = "customer")
@Entity
public class Reservation {

    @OneToOne
     private Customer customer;
    @OneToMany
    private List<Room> room;
    @OneToOne
    private Payment payment;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @NotNull
    private Date checkIn;
    @NotNull
    private Date checkOut;
    private String referenceNo;

}
