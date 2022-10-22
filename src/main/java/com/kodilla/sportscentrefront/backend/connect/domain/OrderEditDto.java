package com.kodilla.sportscentrefront.backend.connect.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEditDto {

    private Long orderId;
    private String description;
    private BigDecimal sum;
}
