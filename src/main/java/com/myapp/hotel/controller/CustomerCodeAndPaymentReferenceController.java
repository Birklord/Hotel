package com.myapp.hotel.controller;

import com.myapp.hotel.model.CustomerCodeAndPaymentReference;
import com.myapp.hotel.service.CustomerCodeAndPaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/customercodeandpaymentreservation")
public class CustomerCodeAndPaymentReferenceController {
    @Autowired
    private CustomerCodeAndPaymentReferenceService customerCodeAndPaymentReservationService;
    private CustomerCodeAndPaymentReference customerCodeAndPaymentReference;
    static Logger logger = Logger.getLogger(String.valueOf(CustomerCodeAndPaymentReference.class));



    @GetMapping("/retrieveall")
    public List<CustomerCodeAndPaymentReference> findAll(){
        return customerCodeAndPaymentReservationService.findAll();
    }

    @GetMapping("/retrievebyid")
    public Optional<CustomerCodeAndPaymentReference> findById(@PathVariable Long Id){
        return customerCodeAndPaymentReservationService.findById(Id);
    }

}