package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CardClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    @EventListener(ApplicationReadyEvent.class)
    public Card[] getCards() {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getCard())
                .build()
                .encode()
                .toUri();

        Card[] response = restTemplate.getForObject(
                uri, Card[].class
        );
        //TODO Delete sout
        System.out.println(response.length);
        return response;
    }
}
