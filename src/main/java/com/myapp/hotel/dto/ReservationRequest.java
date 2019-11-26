package com.myapp.hotel.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ReservationRequest {
    private  Long id;
    @NotNull
    private Date checkIn;
    @NotNull
    private Date checkOut;

    private String referenceNo;
}
