package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Payment {


    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Reservation reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date paymentDate;


}
