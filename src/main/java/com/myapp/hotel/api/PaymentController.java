package com.myapp.hotel.api;


import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Payment;
import com.myapp.hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("api/v1/payment")
@RestController
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/save")
    public Boolean addCustomer(@Valid @NonNull @RequestBody PaymentRequest paymentRequest) {
        Boolean isSaved = paymentService.addPayment(paymentRequest);
        return isSaved;
    }

    @GetMapping("/retrieveall")
    public List<Payment> getAllPayments() {
        return paymentService.findAllPayment();
    }

    @GetMapping("/retrievebyid/{id}")
    public Payment getPaymentById(@PathVariable("id") Long id) {
        return paymentService.findPaymentById()
                .orElse(null);
    }

}
