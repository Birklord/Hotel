package com.myapp.hotel.controller;

import com.myapp.hotel.dto.ReservationRequest;
import com.myapp.hotel.dto.ResponseModel;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.model.Reservation;
import com.myapp.hotel.repository.CustomerRepository;
import com.myapp.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/reservation")
@RestController
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private CustomerRepository customerRepository;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @PostMapping("/save")
    public ResponseModel addReservation(@RequestBody ReservationRequest reservationRequest){
        ResponseModel responseModel = new ResponseModel();
        Optional<Customer> customer =customerRepository.findById(reservationRequest.getCustomerId());
        Boolean isSaved = false;
        if (customer.isPresent()) {
            try {
                isSaved = reservationService.addReservation(reservationRequest);
                isSaved = true;
//                responseModel.setData();
                responseModel.setResponseCode("00");
                responseModel.setValid(true);
                responseModel.setResponseMessage("Successfully added");
            }
            catch(Exception e){
                e.printStackTrace();
                e.getMessage();
//                responseModel.setData(null);
                responseModel.setResponseCode("99");
                responseModel.setValid(false);
                responseModel.setResponseMessage("Failed to adding");
            }
        }
            else{
                System.out.println("invalid customer");
//                responseModel.setData(null);
//                responseModel.setResponseCode("99");
//                responseModel.setValid(false);
//                responseModel.setResponseMessage("Failed to adding");
            }



        return responseModel;
    }
    @GetMapping("retrieveall")
    public List<Reservation> getAllReservation(){
        List<Reservation> isFound = reservationService.findAllReservation();
        return isFound;
    }

    @GetMapping("retrievebyid/{id}")
    public Optional<Reservation> getReservationById(@PathVariable Long id){
        return reservationService.findReservationById(id);
    }
}
