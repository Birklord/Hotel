package com.myapp.hotel.service;

import com.myapp.hotel.model.CustomerCodeAndPaymentReference;

import java.util.List;
import java.util.Optional;

public interface CustomerCodeAndPaymentReferenceService {

    public List<CustomerCodeAndPaymentReference> findAll();
    public Optional<CustomerCodeAndPaymentReference> findById(Long Id);
}
