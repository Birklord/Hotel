package com.myapp.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/CustomerCodeAndPaymentReservation")
public class CustomerCodeAndPaymentReservation {
    @Autowired
    private CustomerCodeAndPaymentReservation customerCodeAndPaymentReservation;


    @GetMapping("/retrieveall")
    public String findAll(){
        return customerCodeAndPaymentReservation.findAll();
    }

    @GetMapping("/retrievebyid")
    public String findById(@PathVariable Long Id){
        return customerCodeAndPaymentReservation.findById(Id);
    }

}
