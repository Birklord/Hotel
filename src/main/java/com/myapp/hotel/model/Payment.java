package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    private String transactionDate;
    private String transactionAmount;
    private String paystackAuthorizationUrl;
    private Boolean paystackStatus;
    private String paystackMessage;
    private String status;
    private String paystackAccessCode;
    private String paystackReference;


}
