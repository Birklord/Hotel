package com.myapp.hotel.service;

import com.myapp.hotel.dto.ReservationRequest;
import com.myapp.hotel.model.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    public Boolean addReservation(ReservationRequest reservationRequest);
    public Optional<Reservation> findReservationById(Long id);
    List<Reservation> findAllReservation();
}
