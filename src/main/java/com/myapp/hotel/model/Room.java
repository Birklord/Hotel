package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Room {
   @ManyToOne
   @JoinColumn
   private Reservation reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String number;
    private String status;
    private String categoryName;
    private Integer categoryAmount;

   // @ManyToOne
   // private Set<Reservation> reservations;

   // @OneToOne(mappedBy = "reservation")
  //  @JoinColumn
  //  private Set<Reservation> reservations;


}