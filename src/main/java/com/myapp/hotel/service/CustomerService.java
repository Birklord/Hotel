package com.myapp.hotel.service;
import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomerService {


     Customer findCustomer(Long id);
     public Boolean addCustomer(CustomerRequest customerRequest);

     public BaseModel findCustomerById(Long id);

     List<BaseModel> findAllCustomer();

     CustomerRequest convertToDto(Customer customer);
}
