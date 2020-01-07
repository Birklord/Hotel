package com.myapp.hotel.service.impl;

import com.myapp.hotel.model.CustomerCodeAndPaymentReference;
import com.myapp.hotel.repository.CustomerCodeAndPaymentReferenceRepository;
import com.myapp.hotel.service.CustomerCodeAndPaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerCodeAndPaymentReferenceImpl implements CustomerCodeAndPaymentReferenceService {
    @Autowired
    private CustomerCodeAndPaymentReferenceRepository customerCodeAndPaymentReferenceRepository;

    @Autowired
    public List<CustomerCodeAndPaymentReference> findAll(){
        return customerCodeAndPaymentReferenceRepository.findAll();
    }

    @Override
    public Optional<CustomerCodeAndPaymentReference> findById(Long Id) {
        return customerCodeAndPaymentReferenceRepository.findById(Id);
    }

}
