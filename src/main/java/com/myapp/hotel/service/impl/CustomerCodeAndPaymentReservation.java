package com.myapp.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerCodeAndPaymentReservation {
    @Autowired
    private CustomerCodeAndPaymentReservation customerCodeAndPaymentReservation;


    public List<CustomerCodeAndPaymentReservation> findAll(){
        return customerCodeAndPaymentReservation.findAll();
    }

    public List<CustomerCodeAndPaymentReservation> findById(){
        return customerCodeAndPaymentReservation.findById();

    }
}
