package com.myapp.hotel.service;

import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    public Boolean addPayment(PaymentRequest paymentRequest);
    public Optional<Payment> findPaymentById(Long id);
    List<Payment> findAllPayment();
}
