package com.kodilla.sportscentrefront.view.user;

import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserClient;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountOutDto;
import com.kodilla.sportscentrefront.backend.connect.domain.Card;
import com.kodilla.sportscentrefront.backend.connect.domain.CardEditDto;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("User Card")
@CssImport("./styles/views/login/login-view.css")
public class UserCardView extends VerticalLayout {

    private final PasswordField oldPass = new PasswordField("Old Password");
    private final PasswordField newPass1 = new PasswordField("New Password");
    private final PasswordField newPass2 = new PasswordField("Repeat New Password");

    private final Button changeButton = new Button("Change Password");

    private Card card;

    public  UserCardView(UserCardClient userCardClient, CardClient cardClient, UserClient userClient) {
        setId("login-view");

        H1 cardEditHeading = new H1("Edit password to card");

        oldPass.addValueChangeListener(event -> checkButton());
        newPass1.addValueChangeListener(event -> checkButton());
        newPass2.addValueChangeListener(event -> checkButton());
        changeButton.setEnabled(false);

        add(cardEditHeading, oldPass, newPass1, newPass2, changeButton);

        User user = VaadinSession.getCurrent().getAttribute(AccountOutDto.class).getUser();
        card = userCardClient.getCardByUserId(user.getUserId());
        card.setUser(userClient.getUser(user.getUserId()));


        changeButton.addClickListener(event -> {
            if (oldPass.getValue().equals(card.getAccessPass())) {
                if (newPass1.getValue().equals(newPass2.getValue())) {
                    CardEditDto cardEditDto = new CardEditDto(
                            card.getCardId(),
                            card.getUser(),
                            newPass1.getValue(),
                            card.getCardStatus()
                    );
                    cardClient.editCard(cardEditDto);
                    Notification.show("Successfully changed password!");
                } else {
                    Notification.show("Entered new passwords aren't the same!");
                }
            } else {
                Notification.show("Old password is incorrect!");
            }

            oldPass.setValue("");
            newPass1.setValue("");
            newPass2.setValue("");

            checkButton();

            card = userCardClient.getCardByUserId(user.getUserId());
            card.setUser(userClient.getUser(user.getUserId()));
        });
    }

    private void checkButton() {
        changeButton.setEnabled(!oldPass.getValue().isEmpty() &&
                !newPass1.getValue().isEmpty() &&
                !newPass2.getValue().isEmpty());
    }
}
