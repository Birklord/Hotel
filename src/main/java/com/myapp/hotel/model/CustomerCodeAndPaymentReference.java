package com.myapp.hotel.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CustomerCodeAndPaymentReference {

    @Id
    private Long Id;

    private String customerCode;
    private String paymentReference;

}
