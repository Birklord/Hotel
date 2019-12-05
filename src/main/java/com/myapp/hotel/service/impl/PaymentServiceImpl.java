package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Payment;
import com.myapp.hotel.repository.PaymentRepository;
import com.myapp.hotel.service.PaymentService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public  class PaymentServiceImpl implements PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(RoomServiceImpl.class));

    public PaymentServiceImpl(PaymentRepository paymentRepository, Mapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }
    @Override
    public Boolean addPayment(PaymentRequest paymentRequest) {
        Payment payment = mapper.map(paymentRequest, Payment.class);
        Boolean saved=false;
        try{
           paymentRepository.save(payment);
            saved = true;
            logger.info("success");
        }
        catch(Exception e){
            saved=false; e.printStackTrace(); e.getMessage(); logger.severe("failed");
        }
        return saved;
    }

    @Override
    public PaymentRequest convertToDto(Payment payment){
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setId(payment.getId());
        paymentRequest.setTransactionDate(payment.getTransactionDate());
        paymentRequest.setTransactionAmount(payment.getTransactionAmount());
        return paymentRequest;
    }

    @Override
    public BaseModel findPaymentById(Long id) {

        try{
            Optional<Payment> payment = paymentRepository.findById(id);
            if(payment.get()!= null){
                return convertToDto(payment.get());
            }
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace(); e.getMessage(); logger.severe("failed");
        }
       return null;
    }

    @Override
    public List<BaseModel> findAllPayment() {

        List<BaseModel> responseData = new ArrayList<>();
        try{
            paymentRepository.findAll().stream().forEach(payment-> responseData.add(convertToDto((Payment) payment)));
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace(); e.getMessage(); logger.severe("failed");
        }
        return paymentRepository.findAll();
    }
}


