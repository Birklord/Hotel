package com.myapp.hotel.api;


import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;

import com.myapp.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

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

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping(path="{id}")
    public Customer getCustomerById(@PathVariable("id") Long id){
        return customerService.getCustomerById(id)
                .orElse(null);
    }
/*
    @GetMapping
    public List<Customer> getDeleteCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping(path="{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id")UUID id){
        personService.deletePerson(id);
    }
    @PutMapping(path="{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
    */

}