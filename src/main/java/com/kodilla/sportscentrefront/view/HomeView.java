package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.domain.CardCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.CardEditDto;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.CardStatus;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("SportHome")
@Route("sport/home")
public class HomeView extends VerticalLayout {

    @Autowired
    public HomeView(CardClient cardClient) {
        Button button = new Button("Test", event -> {
            System.out.println(cardClient.getCards().length);
        });
        add(button);

        CardCreateDto cardCreateDto = new CardCreateDto(
                null,
                "12345678",
                CardStatus.AVAILABLE
        );
        Button button1 = new Button("TestPost", event -> {
            cardClient.createCard(cardCreateDto);
        });
        add(button1);

        CardEditDto cardEditDto = new CardEditDto(
                2L,
                new User(144L),
                "1234567890",
                CardStatus.BROKEN
        );
        Button button2 = new Button("TestPut", event -> {
            cardClient.editCard(cardEditDto);
        });
        add(button2);

        Button button3 = new Button("TestDelete", event -> {
            cardClient.deleteCard(3L);
        });
        add(button3);
    }
}
