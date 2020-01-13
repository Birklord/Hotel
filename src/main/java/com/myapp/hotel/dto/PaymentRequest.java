package com.myapp.hotel.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PaymentRequest extends BaseModel{

    private  Long id;
    @NotNull
    private Date transactionDate;
    @NotNull
    private String transactionAmount;
    private Long reservationId;
    private String paystackAuthorizationUrl;
    private String paystackAccessCode;
    private String paystackReference;

}

