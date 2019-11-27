package com.myapp.hotel.api;

import com.myapp.hotel.dto.ReservationRequest;
import com.myapp.hotel.model.Reservation;
import com.myapp.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/reservation")
@RestController
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @PostMapping("/save")
    public Boolean addReservation(ReservationRequest reservationRequest){
        Boolean isSaved = reservationService.addReservation(reservationRequest);
        return isSaved;
    }
    @GetMapping("retrieveall")
    public List<Reservation> getAllReservation(){
        List<Reservation> isFound = reservationService.findAllReservation();
        return isFound;
    }

    @GetMapping(path = "{id}")
    public Optional<Reservation> getReservationById(Long id){
        return reservationService.findReservationById(id);
    }
}
