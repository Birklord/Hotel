package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.repository.CustomerRepository;
import com.myapp.hotel.service.CustomerService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.logging.LoggingProxy;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(CustomerServiceImpl.class));

    public CustomerServiceImpl(CustomerRepository customerRepository, Mapper mapper) {
        this.customerRepository = customerRepository;
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
            logger.severe("This didn't save");
            }
        return saved;
    }

    @Override
    public List<Customer> findAllCustomer() {

        try{
            customerRepository.findAll();
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            logger.severe("Not found");
        }
     return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findCustomerById(Long id) {
        try{
            customerRepository.findById(id);
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            logger.severe("Not found");
        }
        return customerRepository.findById(id);
    }


}
