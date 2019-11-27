package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.repository.CustomerRepository;
import com.myapp.hotel.service.CustomerService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final CustomerService customerService;

    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(CustomerServiceImpl.class));

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerService customerService, Mapper mapper) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    @Override
    public Boolean addCustomer(CustomerRequest customerRequest) {
        Customer customer = mapper.map(customerRequest,Customer.class);
        Boolean saved=false;
        try {
            customerRepository.save(customer);
             saved=true;
            logger.info("This save");
        }
        catch (Exception ex){
            saved=false;
            ex.printStackTrace();
            ex.getMessage();
            logger.severe("This didn't save");
            }
        return saved;
    }
    @Override
    public CustomerRequest convertToDto(Customer customer){
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setId(customer.getId());
        customerRequest.setName(customer.getName());
        customerRequest.setEmail(customer.getEmail());
        customerRequest.setPhoneNo(customer.getPhoneNo());
        customerRequest.setAddress(customer.getAddress());
        return customerRequest;
    }

    @Override
    public List<Customer> findAllCustomer() {

        try{

            List<BaseModel> responseData = new ArrayList<BaseModel>();
            customerService.findAllCustomer().stream().forEach(customer -> responseData.add(customerService.convertToDto(customer)));
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("Not found");
        }
     return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        try{
            customerService.findCustomerById(id);
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("Not found");
        }
        return customerRepository.findById(id);
    }


}
