package com.myapp.hotel.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ReservationRequest extends BaseModel {
    private  Long id;
    @NotNull
    private Date checkIn;
    @NotNull
    private Date checkOut;

    private String referenceNo;

    private Long customerId;
}
