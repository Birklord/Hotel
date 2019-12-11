package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Room   {
   @ManyToOne
   @JoinColumn
   private Reservation reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String roomNumber;
    private String status;
    private String roomType;
    private Long roomTypeAmount;
    private String roomFloor;
    public int maximum;



}