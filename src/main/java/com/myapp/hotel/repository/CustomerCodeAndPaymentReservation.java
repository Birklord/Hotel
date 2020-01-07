package com.myapp.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCodeAndPaymentReservation extends JpaRepository<CustomerCodeAndPaymentReservation, Long> {
}
