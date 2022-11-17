package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class OrderClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public Order[] getOrders() {
        URI uri = getURIAddress();
        return restTemplate.getForObject(
                uri, Order[].class
        );
    }

    public Order getOrder(Long orderId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getOrder() + "/" + orderId)
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(
                uri, Order.class
        );
    }

    public Order createOrder(OrderCreateDto orderCreateDto) {
        URI uri = getURIAddress();

        return restTemplate.postForObject(
                uri, orderCreateDto, Order.class
        );
    }

    public Order editOrder(OrderEditDto orderEditDto) {
        URI uri = getURIAddress();
        restTemplate.put(
                uri, orderEditDto
        );

        return new Order(
                orderEditDto.getOrderId(),
                orderEditDto.getDescription(),
                orderEditDto.getSum(),
                orderEditDto.getUser()
        );
    }

    public void deleteOrder(Long orderId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getOrder() + "/" + orderId)
                .build()
                .encode()
                .toUri();
        restTemplate.delete(uri);
    }

    private URI getURIAddress() {
        return UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getOrder())
                .build()
                .encode()
                .toUri();
    }
}
