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
    private CustomerServiceImpl customerServiceImpl;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(RoomServiceImpl.class));

    public PaymentServiceImpl(PaymentRepository paymentRepository, Mapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }
    @Override
    public Boolean addPayment(PaymentRequest paymentRequest) {
        Reservation reservation = new Reservation();
        Payment payment = mapper.map(paymentRequest, Payment.class);
        Customer customer = customerServiceImpl.findCustomer(payment.getCustomerId());
        Boolean saved = false;
        String status = "s";
        if(customer != null) {
            String payStackUniqueReference = reservation.getId() + PayStackRefNoGenerator();
            CustomerCodeAndPaymentReferenceImpl customerCodeAndPaymentReferenceImpl = new CustomerCodeAndPaymentReferenceImpl();
            Transactions transactions = new Transactions();
            JSONObject jsonObject = transactions.initializeTransaction(payStackUniqueReference, payment.getTransactionAmount(), customer.getEmail(), null, null);
                String paystackReference = (String) ((JSONObject) jsonObject.get("data")).get("reference");
                String paystackAccessCode = (String) ((JSONObject) jsonObject.get("data")).get("access_code");
                String paystackAuthorizationUrl = (String) ((JSONObject) jsonObject.get("data")).get("authorization_url");
                String customerCode = String.valueOf(customer.getCustomerCode());
                String PaymentReference = String.valueOf(paystackReference);
            try {

                payment.setPaystackAuthorizationUrl(paystackAuthorizationUrl);
                payment.setPaystackAccessCode(paystackAccessCode);
                payment.setPaystackReference(paystackReference);
                paymentRepository.save(payment);
                customerCodeAndPaymentReferenceImpl.saveFromPayment(customerCode, payStackUniqueReference, status);
                logger.info("success");
                status = "s";

            } catch (Exception e) {
                saved = false;
                e.printStackTrace();
                e.getMessage();
                logger.severe("failed");
                status= "f";
            }
//            customerCodeAndPaymentReferenceImpl.saveFromPayment(customerCode, payStackUniqueReference, status);
            return saved;
        }
        return saved;
    }

    @Override
    public PaymentRequest convertToDto(Payment payment){
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setId(payment.getId());
        paymentRequest.setTransactionDate(payment.getTransactionDate());
        paymentRequest.setTransactionAmount(payment.getTransactionAmount());
        return paymentRequest;
    }

    @Override
    public BaseModel findPaymentById(Long id) {

        try{
            Optional<Payment> payment = paymentRepository.findById(id);
            if(payment.get()!= null){
                PaymentRequest paymentDetails = convertToDto(payment.get());

                return paymentDetails;
//                return convertToDto(payment.get());
            }
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace(); e.getMessage(); logger.severe("failed");
        }
       return null;
    }

    @Override
    public List<BaseModel> findAllPayment() {

        List<BaseModel> responseData = new ArrayList<>();
        try{
            paymentRepository.findAll().stream().forEach(payment-> responseData.add(convertToDto((Payment) payment)));
            logger.info("Success");
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("failed");
        }
        return paymentRepository.findAll();
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
        String referenceNo = String.valueOf(day() + month() + generator() + generator()+ year());
        return referenceNo;
    }



}


