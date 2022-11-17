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
        return restTemplate.getForObject(
                uri, Card[].class
        );
    }

    public Card getCard(Long cardId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getCard() + "/" + cardId)
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(
                uri, Card.class
        );
    }

    public Card createCard(CardCreateDto cardCreateDto) {
        URI uri = getURIAddress();

        return restTemplate.postForObject(
                uri, cardCreateDto, Card.class
        );
    }

    public Card editCard(CardEditDto cardEditDto) {
        URI uri = getURIAddress();
        restTemplate.put(
                uri, cardEditDto
        );

        return new Card(
                cardEditDto.getCardId(),
                cardEditDto.getUser(),
                cardEditDto.getAccessPass(),
                cardEditDto.getCardStatus()
        );
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
