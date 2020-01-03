package com.myapp.hotel.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Data
public class PaymentRequest extends BaseModel{

    private  Long id;
    @NotNull
    private Date transactionDate;
    @NotNull
    private String transactionAmount;
    private Long customerId;
    private String merchantId;
    private String merchantKey;
    private String channelId;
    private String website;
    private String industryTypeId;
    private String paytmUrl;
    private Map<String, String> details;
}

