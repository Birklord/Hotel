package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Payment {

   @OneToOne
    private Reservation reservation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long customerId;
    private Date transactionDate;
    private String transactionAmount;
    private String paystackAuthorizationUrl;
    private String paystackAccessCode;
    private String paystackReference;


}
