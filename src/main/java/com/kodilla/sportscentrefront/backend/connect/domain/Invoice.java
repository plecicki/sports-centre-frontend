package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Invoice {

    private Long invoiceId;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDeadline;
    private BigDecimal sum;
    private User user;
}
