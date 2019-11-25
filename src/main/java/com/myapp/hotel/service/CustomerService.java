package com.myapp.hotel.service;
import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

     void addCustomer(CustomerRequest customerRequest);

     List<Customer> getAllCustomer();

     Optional<Customer> getCustomerById(Long id);
}
