package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.ReservationRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.model.Reservation;
import com.myapp.hotel.repository.ReservationRepository;
import com.myapp.hotel.service.ReservationService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(ReservationServiceImpl.class));

    public ReservationServiceImpl(ReservationRepository reservationRepository, Mapper mapper) {
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
    }

    @Override
    public Boolean addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = mapper.map(reservationRequest,Reservation.class);
        Boolean saved=false;
       try{
           reservationRepository.save(reservation);
           saved = true;
           logger.info("success");
        }
       catch(Exception e){
       saved=false;
       e.printStackTrace();
       logger.severe("failed");
       }
       return saved;
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
            try{
                reservationRepository.findById(id);
                logger.info("found");
            }
            catch(Exception e){
                e.printStackTrace();
                logger.severe("Not found");
            }
            return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> findAllReservation() {
        try{
            reservationRepository.findAll();
            logger.info("found");
        }
        catch(Exception e){
            e.printStackTrace();
            logger.severe("Not found");
        }
        return reservationRepository.findAll();
    }
    }



