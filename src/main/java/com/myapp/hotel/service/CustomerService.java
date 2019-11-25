package com.myapp.hotel.service;
import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.repository.CustomerRepository;
import com.myapp.hotel.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

public interface CustomerService {


     public Boolean addCustomer(CustomerRequest customerRequest);

     public List<Customer> getAllCustomer();

     public Optional<Customer> getCustomerById(Long id);
}
