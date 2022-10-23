package com.kodilla.sportscentrefront.view;

import com.kodilla.sportscentrefront.backend.connect.client.*;
import com.kodilla.sportscentrefront.backend.connect.domain.*;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.CardStatus;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Home")
@Route("sport/home")
public class HomeView extends VerticalLayout {

    @Autowired
    public HomeView(CardClient cardClient, AccountClient accountClient,
                    InvoiceFrontClient invoiceFrontClient, UserCardClient userCardClient,
                    YouTubeClient youTubeClient) {

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

        AccountCreateDto accountCreateDto = new AccountCreateDto(
                "ju",
                "paord",
                Role.ADMIN,
                new User(45L),
                "Uyfut78tuyUyt"
        );
        Button button4 = new Button("CreateAccount", event -> {
            String message = accountClient.createAccount(accountCreateDto);
            System.out.println("Message: " + message);
        });
        add(button4);
        AccountInDto accountInDto = new AccountInDto(
                "ju",
                "paord"
        );
        Button button5 = new Button("LoginTest", event -> {
            Object message = accountClient.login(accountInDto);
            System.out.println("Message: " + message);
        });
        add(button5);

        Button button6 = new Button("GetInvoiceTest", event -> {
            Invoice[] message = invoiceFrontClient.getInvoiceByUserId(41L);
            System.out.println("Message: " + message);
        });
        add(button6);

        Button button7 = new Button("GetCardTest", event -> {
            Card message = userCardClient.getCardByUserId(45L);
            System.out.println("Message: " + message);
        });
        add(button7);

        Button button8 = new Button("GetYouTube", event -> {
            MyYouTubeDto[] message = youTubeClient.getYouTube();
            System.out.println("Message: " + message);
        });
        add(button8);
    }
}
