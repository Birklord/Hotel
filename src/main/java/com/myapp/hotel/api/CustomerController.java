package com.myapp.hotel.api;


import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;

import com.myapp.hotel.repository.CustomerRepository;
import com.myapp.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {
    //@Autowired
  //  CustomerRepository customerRepository;

    @Autowired
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public Boolean addCustomer(@Valid @NonNull @RequestBody CustomerRequest customerRequest){
       Boolean isSaved= customerService.addCustomer(customerRequest);
       return isSaved;
    }

    @GetMapping("/retrieveall")
    public List<Customer> getAllCustomers() {
        return customerService.findAllCustomer();
    }

    @GetMapping("/retrievebyid/{id}")
    public Customer getCustomerById(@PathVariable("id") Long id){
        return customerService.findCustomerById(id)
                .orElse(null);
    }


}
