package com.myapp.hotel.controller;


import com.myapp.hotel.dto.BaseModel;
import com.myapp.hotel.dto.CustomerRequest;
import com.myapp.hotel.dto.ResponseModel;
import com.myapp.hotel.exception.CustomerNotAddedException;
import com.myapp.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;


@RequestMapping("api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
//    private Customer customer;
//    private Customers customers;//For paystack
    static Logger logger = Logger.getLogger(String.valueOf(CustomerController.class));

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public Boolean addCustomer(@Valid @NonNull @RequestBody CustomerRequest customerRequest){

//        Gson gson = new Gson();
//        String json = gson.toJson(customerRequest);
       Boolean isSaved= false;
        try{
            customerService.addCustomer(customerRequest);
            isSaved=true;
        }
        catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.info("failed");
        }
       return isSaved;
    }
    @ExceptionHandler
    public String handleCustomerNotAddedException(CustomerNotAddedException exception){
        return exception.getMessage();
    }


    @GetMapping("retrieveall")
    public ResponseModel getAllCustomers() {
        ResponseModel responseModel = new ResponseModel();
        try{
            List<BaseModel> customerList = customerService.findAllCustomer();
            //responseModel.data.addAll(customerList);
            responseModel.setData(customerList);
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

    @GetMapping("/retrievebyid/{id}")
    public ResponseModel getCustomerById(@PathVariable("id") Long id) {
        ResponseModel responseModel = new ResponseModel();
         try {

             if (id != null) {
                 responseModel.getData().add(customerService.findCustomerById(id));
                 responseModel.setResponseCode("00");
                 responseModel.setValid(true);
                 responseModel.setResponseMessage("Success fetching customers");
             }
         }
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


}
