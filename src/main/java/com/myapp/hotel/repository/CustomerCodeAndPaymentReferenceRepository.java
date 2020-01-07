package com.myapp.hotel.repository;

import com.myapp.hotel.model.CustomerCodeAndPaymentReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCodeAndPaymentReferenceRepository extends JpaRepository<CustomerCodeAndPaymentReference, Long> {
}
