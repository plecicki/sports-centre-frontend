package com.kodilla.sportscentrefront.backend.connect.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    public Order(Long orderId) {
        this.orderId = orderId;
    }

    private Long orderId;
    private String description;
    private BigDecimal sum;
}
