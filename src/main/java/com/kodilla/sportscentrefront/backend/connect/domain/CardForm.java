package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.CardStatus;
import com.kodilla.sportscentrefront.services.AdminCardService;
import com.kodilla.sportscentrefront.view.admin.AdminCardView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

public class CardForm extends FormLayout {

    private TextField accessPass = new TextField("Access password:");
    private Select<CardStatus> cardStatus = new Select<>();

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Binder<Card> binder = new BeanValidationBinder<Card>(Card.class);

    private AdminCardView adminCardView;

    private AdminCardService adminCardService;

    public CardForm(AdminCardView adminCardView, CardClient cardClient, UserCardClient userCardClient) {
        this.adminCardService = AdminCardService.getInstance(cardClient, userCardClient);

        accessPass.setRequired(true);
        cardStatus.setRequiredIndicatorVisible(true);

        cardStatus.setLabel("Card status:");
        cardStatus.setItems(CardStatus.values());

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(accessPass, cardStatus, buttons);
        this.adminCardView = adminCardView;

        save.addClickListener(event -> save());

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        delete.addClickListener(event -> delete());

        binder.bindInstanceFields(this);
    }

    private void save() {
        Card card = binder.getBean();

        if (card.getCardId() == null) {
            CardCreateDto cardCreateDto = new CardCreateDto(
                    card.getUser(),
                    card.getAccessPass(),
                    card.getCardStatus()
            );
            adminCardService.createCard(cardCreateDto);
        } else {
            CardEditDto cardEditDto = new CardEditDto(
                    card.getCardId(),
                    card.getUser(),
                    card.getAccessPass(),
                    card.getCardStatus()
            );
            adminCardService.editCard(cardEditDto);
        }

        adminCardView.refresh();
        setCard(null);
    }

    private void delete() {
        Card card = binder.getBean();
        adminCardService.delete(card.getCardId());
        adminCardView.refresh();
        setCard(null);
    }

    public void setCard(Card card) {
        binder.setBean(card);

        if (card == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }
}
