package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.Order;
import com.kodilla.sportscentrefront.backend.connect.domain.OrderCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.OrderDecInDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class SupplementsClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public Order createOrder(OrderDecInDto orderDecInDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getSupplements())
                .build()
                .encode()
                .toUri();
        Order response = restTemplate.postForObject(
                uri, orderDecInDto, Order.class
        );

        return response;
    }
}
