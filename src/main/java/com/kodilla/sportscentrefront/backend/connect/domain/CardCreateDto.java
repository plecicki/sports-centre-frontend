package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateDto {
    private User user;
    private String accessPass;
    private CardStatus cardStatus;
}
