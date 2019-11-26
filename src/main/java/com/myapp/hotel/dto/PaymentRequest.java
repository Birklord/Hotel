package com.myapp.hotel.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PaymentRequest {

    private  Long id;
    @NotNull
    private Date paymentDate;
}

