package com.myapp.hotel.service.impl;

import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.model.Customer;
import com.myapp.hotel.model.CustomerCodeAndPaymentReference;
import com.myapp.hotel.model.Payment;
import com.myapp.hotel.repository.CustomerCodeAndPaymentReferenceRepository;
import com.myapp.hotel.repository.PaymentRepository;
import com.myapp.hotel.service.PaymentService;
import me.iyanuadelekan.paystackjava.core.Transactions;
import org.dozer.Mapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public  class PaymentServiceImpl implements PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;
    private CustomerCodeAndPaymentReferenceRepository customerCodeAndPaymentReservationRepository;
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
    private ReservationServiceImpl reservationServiceImpl;
    private final Mapper mapper;
    static Logger logger = Logger.getLogger(String.valueOf(RoomServiceImpl.class));

    public PaymentServiceImpl(PaymentRepository paymentRepository, Mapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }
    @Override
    public Boolean addPayment(PaymentRequest paymentRequest) {
        Payment payment = mapper.map(paymentRequest, Payment.class);
        Customer customer = customerServiceImpl.findCustomer(payment.getCustomerId());
        CustomerCodeAndPaymentReference customerCodeAndPaymentReference = new CustomerCodeAndPaymentReference();

        String payStackCode = String.valueOf(reservationServiceImpl.PayStackRefNoGenerator());
//        if(payStackCode != null)
        if(customer != null) {
            Transactions transactions = new Transactions();
            JSONObject jsonObject = new JSONObject();

                jsonObject = transactions.initializeTransaction(payStackCode, payment.getTransactionAmount(), customer.getEmail(), null, null);
                String paystackReference = (String) ((JSONObject) jsonObject.get("data")).get("reference");
                String paystackAccessCode = (String) ((JSONObject) jsonObject.get("data")).get("access_code");
                String paystackAuthorizationUrl = (String) ((JSONObject) jsonObject.get("data")).get("authorization_url");
            try {
                customerCodeAndPaymentReference.setCustomerCode(customer.getCustomerCode());
                customerCodeAndPaymentReference.setPaymentReference(paystackReference);
                customerCodeAndPaymentReference.setPayStackCode(payStackCode);
                custAndPayRefPersist();
                logger.info("Gotten expected values");
            }
            catch(Exception e){
                        e.getMessage();
                        e.printStackTrace();
                        logger.severe("Duplicate transaction");
            }

            Boolean saved = false;
            try {
                customerCodeAndPaymentReference.setCustomerCode(customer.getCustomerCode());
                customerCodeAndPaymentReference.setPaymentReference(paystackReference);
                custAndPayRefPersist();
                payment.setPaystackAuthorizationUrl(paystackAuthorizationUrl);
                payment.setPaystackAccessCode(paystackAccessCode);
                payment.setPaystackReference(paystackReference);
                paymentRepository.save(payment);

                saved = true;
                logger.info("success");
            } catch (Exception e) {
                saved = false;
                e.printStackTrace();
                e.getMessage();
                logger.severe("failed");
            }
            return saved;
        }
        else return false;
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
            e.printStackTrace(); e.getMessage(); logger.severe("failed");
        }
        return paymentRepository.findAll();
    }

    void custAndPayRefPersist(){
        CustomerCodeAndPaymentReference customerCodeAndPaymentReference = new CustomerCodeAndPaymentReference();
        try{
        customerCodeAndPaymentReservationRepository.save(customerCodeAndPaymentReference);
            logger.info("Successfully persisted");
    }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.severe("Ko ti Shele");
        }
    }




}


