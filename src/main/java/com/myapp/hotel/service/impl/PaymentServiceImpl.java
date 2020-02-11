package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.model.Payment;
import com.myapp.hotel.model.Reservation;
import com.myapp.hotel.repository.PaymentRepository;
import com.myapp.hotel.service.PaymentService;
import me.iyanuadelekan.paystackjava.core.Transactions;
import org.apache.commons.lang3.RandomStringUtils;
import org.dozer.Mapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
@Service
public  class PaymentServiceImpl implements PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;
    @Autowired
    private ReservationServiceImpl reservationServiceImpl;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(RoomServiceImpl.class));

    public PaymentServiceImpl(PaymentRepository paymentRepository, Mapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }
    @Override
    public String addPayment(PaymentRequest paymentRequest) {
        Payment payment = mapper.map(paymentRequest, Payment.class);
        Optional<Reservation> reservation = reservationServiceImpl.findReservationById(paymentRequest.getReservationId());
        Customer customer = customerServiceImpl.findCustomer(reservation.get().getCustomer().getId());
        GregorianCalendar gcal = new GregorianCalendar();
        String tDate = String.valueOf(gcal.get(Calendar.YEAR)+ "-" + monthValue() + "-" +gcal.get(Calendar.DAY_OF_MONTH));
        payment.setTransactionDate(tDate);
        payment.setCustomerId(customer.getId());
        payment.setReservation(reservation.get());
        String payStackUniqueReference = paymentRequest.getReservationId() + PayStackRefNoGenerator();
        String status = "F";
        if(customer != null) {
            Transactions transactions = new Transactions();
            JSONObject jsonObject = transactions.initializeTransaction(payStackUniqueReference, payment.getTransactionAmount(), customer.getEmail(), null, null);
                String paystackReference = (String) ((JSONObject) jsonObject.get("data")).get("reference");
                String paystackAccessCode = (String) ((JSONObject) jsonObject.get("data")).get("access_code");
                String paystackAuthorizationUrl = (String) ((JSONObject) jsonObject.get("data")).get("authorization_url");
                String paystackMessage = (String) jsonObject.get("message");
                Boolean paystackStatus = (Boolean) jsonObject.get("status");
                if(paystackStatus = true){
                    status = "S";
                    paymentValues(paystackAuthorizationUrl, paystackAccessCode, paystackReference, status);
                }
                else{
                    status= "F";
                    failedPaymentResponse(paystackMessage, status); }
        }
            else{
                return "invalid customer";
            }
             return null;

    }

     public String paymentValues(String paystackAuthorizationUrl, String paystackAccessCode, String paystackReference, String status ){
        Payment payment = new Payment();
         try {
             payment.setPaystackAuthorizationUrl(paystackAuthorizationUrl);
             payment.setPaystackAccessCode(paystackAccessCode);
             payment.setPaystackReference(paystackReference);
             payment.setStatus(status);
             paymentRepository.save(payment);
             logger.info("success");
             return "payment successful";
         }
         catch (Exception e) {
             e.printStackTrace();
             e.getMessage();
             logger.severe("failed");
             return "Failed to save payment";
         }
     }


    public String failedPaymentResponse(String paystackMessage, String status){
        Payment payment = new Payment();
        try {
            payment.setPaystackMessage(paystackMessage);
            payment.setStatus(status);
            logger.info("success");
            paymentRepository.save(payment);
            return "payment successful";
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            logger.severe("failed");
            return paystackMessage;
        }
    }

    @Override
    public PaymentRequest convertToDto(Payment payment){
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setId(payment.getId());
        return paymentRequest;
    }

    @Override
    public BaseModel findPaymentById(Long id) {

        try{
            Optional<Payment> payment = paymentRepository.findById(id);
            if(payment.get()!= null){
                PaymentRequest paymentDetails = convertToDto(payment.get());
                return paymentDetails;
            }
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace(); e.getMessage(); logger.severe("failed");
        }
       return null;
    }

    @Override
    public List<BaseModel> findAllPayment() {
        List<BaseModel> payment = paymentRepository.findAll();
        try{
            logger.info("Success");
            return payment;

        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("failed");
        }
        return null;
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

    public String PayStackRefNoGenerator(){
        String referenceNo = String.valueOf(  generator()+ day() + month()+ year() +generator() );
        return referenceNo;
    }

    public String monthValue(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String month[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        String monthValue= month[gregorianCalendar.get(Calendar.MONTH)];
        return monthValue;
    }

}


