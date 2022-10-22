package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.Card;
import com.kodilla.sportscentrefront.backend.connect.domain.CardCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.CardEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CardClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public Card[] getCards() {
        URI uri = getURIAddress();
        Card[] response = restTemplate.getForObject(
                uri, Card[].class
        );
        return response;
    }

    public Card getCard(Long cardId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getCard() + "/" + cardId)
                .build()
                .encode()
                .toUri();
        Card response = restTemplate.getForObject(
                uri, Card.class
        );
        return response;
    }

    public Card createCard(CardCreateDto cardCreateDto) {
        URI uri = getURIAddress();
        Card response = restTemplate.postForObject(
                uri, cardCreateDto, Card.class
        );

        return response;
    }

    public Card editCard(CardEditDto cardEditDto) {
        URI uri = getURIAddress();
        restTemplate.put(
                uri, cardEditDto
        );

        Card response = new Card(
                cardEditDto.getCardId(),
                cardEditDto.getUser(),
                cardEditDto.getAccessPass(),
                cardEditDto.getCardStatus()
        );
        return response;
    }

    public void deleteCard(Long cardId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getCard() + "/" + cardId)
                .build()
                .encode()
                .toUri();
        restTemplate.delete(uri);
    }

    private URI getURIAddress() {
        return UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getCard())
                .build()
                .encode()
                .toUri();
    }
}
