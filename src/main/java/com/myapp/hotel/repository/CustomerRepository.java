package com.myapp.hotel.repository;

import com.myapp.hotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository<customer> extends JpaRepository<Customer, Long> {

}

