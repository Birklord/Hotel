package com.myapp.hotel.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PaymentRequest extends BaseModel{

    private  Long id;
    @NotNull
    private String transactionAmount;
    @NotNull
    private Long reservationId;

}

