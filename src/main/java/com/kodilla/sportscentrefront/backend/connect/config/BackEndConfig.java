package com.kodilla.sportscentrefront.backend.connect.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class BackEndConfig {

    @Value("${backend.server.endpoint}")
    private String endpoint;

    @Value("${backend.card}")
    private String card;

    @Value("${backend.invoice}")
    private String invoice;

    @Value("${backend.order}")
    private String order;

    @Value("${backend.user}")
    private String user;

    @Value("${backend.account}")
    private String account;

    @Value("${backend.invoice.status}")
    private String status;

    @Value("${backend.supplements}")
    private String supplements;

    @Value("${backend.usercard}")
    private String usercard;

    @Value("${backend.weather}")
    private String weather;

    @Value("${backend.youtube}")
    private String youtube;
}
