package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    public Invoice(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    private Long invoiceId;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDeadline;
    private BigDecimal sum;
    private User user;
}
