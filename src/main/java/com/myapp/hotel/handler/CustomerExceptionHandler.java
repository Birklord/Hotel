package com.myapp.hotel.handler;

import com.myapp.hotel.exception.CustomerNotAddedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler
    public String handleCustomerNotAddedException(CustomerNotAddedException exception){
         return exception.getMessage();
    }

}