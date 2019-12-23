package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.repository.CustomerRepository;
import com.myapp.hotel.service.CustomerService;
import me.iyanuadelekan.paystackjava.core.Customers;
import org.dozer.Mapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private  CustomerRepository customerRepository;
    private Customers customers;

    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(CustomerServiceImpl.class));

    public CustomerServiceImpl(Mapper mapper) {
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
        customerRequest.setFirstName(customer.getFirstName());
        customerRequest.setEmail(customer.getEmail());
        customerRequest.setLastName(customer.getLastName());
        customerRequest.setPhone(customer.getPhone());
        customerRequest.setAddress(customer.getAddress());
        return customerRequest;
    }

    @Override
    public List<BaseModel> findAllCustomer() {
        List<BaseModel> responseData = new ArrayList<>();
        try{
            customerRepository.findAll().stream().forEach(customer -> responseData.add(convertToDto((Customer) customer)));
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("Not found");
        }
     return responseData;
    }

    @Override
    public BaseModel findCustomerById(Long id) {
        try{
            Optional<Customer> customer = customerRepository.findById(id);
            if(customer.get()!= null){
                return convertToDto(customer.get());
            }
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("Not found");
        }
        return null;
    }


}
