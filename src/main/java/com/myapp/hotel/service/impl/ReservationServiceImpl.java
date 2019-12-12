package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.dto.ReservationRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.model.Reservation;
import com.myapp.hotel.repository.CustomerRepository;
import com.myapp.hotel.repository.ReservationRepository;
import com.myapp.hotel.service.ReservationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    private CustomerRepository customerRepository;
    private Customer customer;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(ReservationServiceImpl.class));

    public ReservationServiceImpl(ReservationRepository reservationRepository, Mapper mapper) {
        this.reservationRepository = reservationRepository;
        this.mapper = mapper;
    }
    public String generator(){
        String gen = RandomStringUtils.randomAlphanumeric(3);
        return gen;
    }
    public String month(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String month[] = { "JA", "FE", "MA", "AP", "MA", "JU", "JU", "AU", "SE", "OC", "NO", "DE","ER" };
        switch(gregorianCalendar.get(Calendar.MONTH)){

            case 0:
                return month[0];
            case 1:
                return month[1];
            case 2:
                return month[2];
            case 3:
                return month[3];
            case 4:
                return month[4];
            case 5:
                return month[5];
            case 6:
                return month[6];
            case 7:
                return month[7];
            case 8:
                return month[8];
            case 9:
                return month[9];
            case 10:
                return month[10];
            case 11:
                return month[11];
        }
        return month[12];
    }
    public String year(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        String sub = year.substring(2);

        return sub;
    }

    @Override
    public ReservationRequest convertToDto(Reservation reservation){
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setId(reservation.getId());
        reservationRequest.setCheckIn(reservation.getCheckIn());
        reservationRequest.setCheckOut(reservation.getCheckOut());
        reservationRequest.setReferenceNo(reservation.getReferenceNo());

        return reservationRequest;
    }

    @Override
    public Boolean addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = mapper.map(reservationRequest,Reservation.class);
        String cust = String.valueOf(customerRepository.findById(reservationRequest.getCustomerId()));
        if(cust.isEmpty()) {
            return false;
        }
        else
        reservation.setCustomer(customer);
        Boolean saved=false;
       try{
           reservationRepository.save(reservation); saved = true; logger.info("success");

        }
       catch(Exception e){
       saved=false; e.printStackTrace(); logger.severe("failed");
       }
       return saved;
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
            try{
                reservationRepository.findById(id); logger.info("found");
            }
            catch(Exception e){
                e.printStackTrace(); logger.severe("Not found");
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
            e.printStackTrace(); logger.severe("Not found");
        }
        return reservationRepository.findAll();
    }
    }



