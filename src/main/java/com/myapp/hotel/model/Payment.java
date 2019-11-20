package com.myapp.hotel.model;

import lombok.Data;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
