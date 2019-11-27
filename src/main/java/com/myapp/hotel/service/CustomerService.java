package com.myapp.hotel.service;
import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomerService {


     public Boolean addCustomer(CustomerRequest customerRequest);

     public Optional<Customer> findCustomerById(Long id);

     List<Customer> findAllCustomer();

     CustomerRequest convertToDto(Customer customer);
}
