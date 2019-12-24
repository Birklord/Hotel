package com.myapp.hotel.dto;

import com.myapp.hotel.model.Customer;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class PaymentRequest extends BaseModel{

    private  Long id;
    @NotNull
    private Date transactionDate;
    @NotNull
    private String transactionAmount;

    private Long customerId;
}

