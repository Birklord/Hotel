package com.myapp.hotel.service.impl;

import com.myapp.hotel.model.CustomerCodeAndPaymentReference;
import com.myapp.hotel.repository.CustomerCodeAndPaymentReferenceRepository;
import com.myapp.hotel.service.CustomerCodeAndPaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomerCodeAndPaymentReferenceImpl implements CustomerCodeAndPaymentReferenceService {
    @Autowired
    private CustomerCodeAndPaymentReferenceRepository customerCodeAndPaymentReferenceRepository;
    private CustomerCodeAndPaymentReference customerCodeAndPaymentReference;
    static Logger logger = Logger.getLogger(String.valueOf(CustomerCodeAndPaymentReference.class));

    @Override
    public Boolean addCustCodeAndPayRef(CustomerCodeAndPaymentReference customerCodeAndPaymentReference){
        Boolean isSaved = false;
        try{
            customerCodeAndPaymentReferenceRepository.save(customerCodeAndPaymentReference);
            isSaved = true;
            logger.info("Saved");
        }catch(Exception e){
            e.printStackTrace();
            logger.severe("Failed");
            isSaved = false;
        }
        return isSaved;
    }

    @Override
    public List<CustomerCodeAndPaymentReference> findAll(){
        return customerCodeAndPaymentReferenceRepository.findAll();
    }

    @Override
    public Optional<CustomerCodeAndPaymentReference> findById(Long Id) {
        return customerCodeAndPaymentReferenceRepository.findById(Id);
    }

    public void saveFromPayment(String customerCode, String payStackUniqueReference, String status) {
        customerCodeAndPaymentReference.setCustomerCode(customerCode);
        customerCodeAndPaymentReference.setPayStackUniqueReference(payStackUniqueReference);
        customerCodeAndPaymentReference.setStatus(status);
        customerCodeAndPaymentReferenceRepository.save(customerCodeAndPaymentReference);
    }
}
