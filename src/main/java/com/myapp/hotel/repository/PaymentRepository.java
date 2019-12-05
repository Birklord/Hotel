package com.myapp.hotel.repository;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
 public interface PaymentRepository<payment> extends JpaRepository<Payment, Long> {

       public BaseModel findPaymentById(Long id);
}


