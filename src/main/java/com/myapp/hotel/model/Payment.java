package com.myapp.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
@ConfigurationProperties("paytm.payment.sandbox")
@Entity
public class Payment {

   @OneToOne
    private Reservation reservations;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long customerId;
    private Date transactionDate;
    private String transactionAmount;
    private String merchantId;
    private String merchantKey;
    private String channelId;
    private String website;
    private String industryTypeId;
    private String paytmUrl;
    @Transient
    private Map<String, String> details;


}
