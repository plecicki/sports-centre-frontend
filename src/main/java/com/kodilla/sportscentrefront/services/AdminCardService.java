package com.kodilla.sportscentrefront.services;

import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.domain.Card;
import com.kodilla.sportscentrefront.backend.connect.domain.CardCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.CardEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminCardService {

    private List<Card> cards;
    private static AdminCardService adminCardService;

    private CardClient cardClient;
    private UserCardClient userCardClient;

    @Autowired
    private AdminCardService(CardClient cardClient, UserCardClient userCardClient) {
        this.cards = Arrays.asList(cardClient.getCards());
        this.cardClient = cardClient;
        this.userCardClient = userCardClient;
    }

    public static AdminCardService getInstance(CardClient cardClient, UserCardClient userCardClient) {
        if (adminCardService == null) {
            adminCardService = new AdminCardService(cardClient, userCardClient);
        }
        return adminCardService;
    }

    public List<Card> getCards() {
        return new ArrayList<>(Arrays.asList(cardClient.getCards()));
    }

    public List<Card> findByCardId(Long cardId) {
        return cards.stream()
                .filter(card -> card.getCardId().toString().contains(cardId.toString()))
                .collect(Collectors.toList());
    }

    public void createCard(CardCreateDto cardCreateDto) {
        cardClient.createCard(cardCreateDto);
    }

    public void editCard(CardEditDto cardEditDto) {
        cardClient.editCard(cardEditDto);
    }

    public void delete(Long cardId) {
        userCardClient.deleteCard(cardId);
    }
}
