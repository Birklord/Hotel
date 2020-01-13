package com.myapp.hotel.service;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Payment;

import java.util.List;

public interface PaymentService {
    public String addPayment(PaymentRequest paymentRequest);
    public PaymentRequest convertToDto(Payment payment);
    public BaseModel findPaymentById(Long id);
    List<BaseModel> findAllPayment();
}
