package com.myapp.hotel.repository;

import com.myapp.hotel.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
 public interface PaymentRepository<payment> extends JpaRepository<Payment, Long> {

}


