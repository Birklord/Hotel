package com.myapp.hotel.service;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    public Boolean addPayment(PaymentRequest paymentRequest);
    public PaymentRequest convertToDto(Payment payment);
    public BaseModel findPaymentById(Long id);
    List<BaseModel> findAllPayment();
}
