package com.myapp.hotel.api;


import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.PaymentRequest;
import com.myapp.hotel.dto.ResponseModel;
import com.myapp.hotel.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("api/v1/payment")
@RestController
public class PaymentController {
    @Autowired
    private  PaymentService paymentService;
    static Logger logger = Logger.getLogger(String.valueOf(PaymentController.class));

    @PostMapping("/save")
    public Boolean addCustomer(@Valid @NonNull @RequestBody PaymentRequest paymentRequest) {
        Boolean isSaved = paymentService.addPayment(paymentRequest);
        return isSaved;
    }

    @GetMapping("/retrieveall")
    public ResponseModel getAllPayments() {
        ResponseModel responseModel = new ResponseModel();
        try{
            List<BaseModel> paymentList = paymentService.findAllPayment();
            //responseModel.data.addAll(customerList);
            responseModel.setData(paymentList);
            responseModel.setResponseCode("00");
            responseModel.setValid(true);
            responseModel.setResponseMessage("Success fetching customers");}
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            responseModel.setData(null);
            responseModel.setResponseCode("99");
            responseModel.setValid(false);
            responseModel.setResponseMessage("Failure fetching customers");
            logger.severe("Failure fetching customers");
        }

        return responseModel;
    }

    @GetMapping("retrievebyid/{id}")
    public ResponseModel getPaymentById(@PathVariable("id") Long id) {
        ResponseModel responseModel = new ResponseModel();
        try {
                responseModel.getData().add(paymentService.findPaymentById(id));
                responseModel.setResponseCode("00");
                responseModel.setValid(true);
                responseModel.setResponseMessage("Success fetching customers");
                logger.info("Succes fetching payment");
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            responseModel.setData(null);
            responseModel.setResponseCode("99");
            responseModel.setValid(false);
            responseModel.setResponseMessage("Failure fetching customers");
            logger.severe("Failure fetching payment");

        }
        return responseModel;
    }

}
