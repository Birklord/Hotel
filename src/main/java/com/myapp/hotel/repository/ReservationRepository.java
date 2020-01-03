package com.myapp.hotel.repository;

import com.myapp.hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
 public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Optional<Reservation> findById(Long id);
}
