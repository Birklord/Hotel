package com.myapp.hotel.controller;

import com.myapp.hotel.model.CustomerCodeAndPaymentReference;
import com.myapp.hotel.service.CustomerCodeAndPaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/CustomerCodeAndPaymentReservation")
public class CustomerCodeAndPaymentReferenceController {
    @Autowired
    private CustomerCodeAndPaymentReferenceService customerCodeAndPaymentReservationService;


    @GetMapping("/retrieveall")
    public List<CustomerCodeAndPaymentReference> findAll(){
        return customerCodeAndPaymentReservationService.findAll();
    }

    @GetMapping("/retrievebyid")
    public Optional<CustomerCodeAndPaymentReference> findById(@PathVariable Long Id){
        return customerCodeAndPaymentReservationService.findById(Id);
    }

}
