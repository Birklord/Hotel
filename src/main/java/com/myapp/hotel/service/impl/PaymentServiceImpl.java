package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Payment;
import com.myapp.hotel.repository.PaymentRepository;
import com.myapp.hotel.service.PaymentService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        Payment payment = mapper.map(paymentRequest,Payment.class);
        Boolean saved=false;
        try{
           paymentRepository.save(payment);
            saved = true;
            logger.info("success");
        }
        catch(Exception e){
            saved=false;
            e.printStackTrace();
            logger.severe("failed");
        }
        return saved;
    }


    @Override
    public Optional<Payment> findPaymentById(Long id) {
        try{
            paymentRepository.findById(id);
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace();
            logger.severe("failed");
        }
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> findAllPayment() {
        try{
            paymentRepository.findAll();
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace();
            logger.severe("failed");
        }
        return paymentRepository.findAll();
    }
}
