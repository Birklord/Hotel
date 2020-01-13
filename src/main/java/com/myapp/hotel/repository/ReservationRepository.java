package com.myapp.hotel.repository;

import com.myapp.hotel.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
 public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
