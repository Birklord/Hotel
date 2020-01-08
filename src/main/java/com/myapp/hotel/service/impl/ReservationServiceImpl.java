package com.myapp.hotel.service.impl;

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
    @Autowired
    private CustomerRepository customerRepository;
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
        String monthValue= month[gregorianCalendar.get(Calendar.MONTH)];
        return monthValue;
    }

    public String day(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String dayValue = String.valueOf(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
        return dayValue;
    }
    public String year(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String year = String.valueOf(gregorianCalendar.get(Calendar.YEAR));
        String sub = year.substring(2);

        return sub;
    }

    public String referenceNo(){
        String referenceNo = String.valueOf(generator() + month() + generator()+ year()).toUpperCase();
        return referenceNo;
    }

    public String PayStackRefNoGenerator(){
        String referenceNo = String.valueOf(day() + month() + generator() + generator()+ year());
        return referenceNo;
    }

    @Override
    public ReservationRequest convertToDto(Reservation reservation){
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setId(reservation.getId());
        reservationRequest.setCheckIn(reservation.getCheckIn());
        reservationRequest.setCheckOut(reservation.getCheckOut());

        return reservationRequest;
    }



    @Override
    public Boolean addReservation(ReservationRequest reservationRequest) {
        Reservation reservation = mapper.map(reservationRequest,Reservation.class);
        Optional<Customer> customer =customerRepository.findById(reservationRequest.getCustomerId());
        Boolean saved = false;

        try {
            reservation.setReferenceNo(referenceNo());
            reservation.setCustomer(customer.get());
            reservationRepository.save(reservation);
            saved = true;
            logger.info("success");
        } catch (Exception e) {
            saved = false;
            e.printStackTrace();
            logger.severe("failed");

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
            e.printStackTrace();
            logger.severe("Not found");
        }
        return reservationRepository.findAll();
    }
    }



