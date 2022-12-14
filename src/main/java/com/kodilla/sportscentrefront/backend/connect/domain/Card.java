package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    public Card(Long cardId) {
        this.cardId = cardId;
    }

    private Long cardId;
    private User user;
    @NotEmpty
    private String accessPass;
    private CardStatus cardStatus;
}
