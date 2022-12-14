package com.kodilla.sportscentrefront.view.admin;

import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.domain.Card;
import com.kodilla.sportscentrefront.backend.connect.domain.CardForm;
import com.kodilla.sportscentrefront.services.AdminCardService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Admin Card")
public class AdminCardView extends VerticalLayout {

    private final AdminCardService cardService;
    private final Grid<Card> grid = new Grid<>(Card.class);
    private final TextField filter = new TextField();
    private final CardForm form;

    public AdminCardView(CardClient cardClient, UserCardClient userCardClient) {
        form = new CardForm(this, cardClient, userCardClient);

        cardService = AdminCardService.getInstance(cardClient, userCardClient);

        filter.setPlaceholder("Filter by id...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("cardId", "accessPass", "cardStatus");

        Button addNewCard = new Button("Add new card");
        addNewCard.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCard(new Card());
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewCard);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setCard(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> {
            form.setCard(grid.asSingleSelect().getValue());
            form.setEnabled(true);
        });
    }

    public void refresh() {
        grid.setItems(cardService.getCards());
    }

    private void update() {
        if (!filter.getValue().isEmpty()) {
            grid.setItems(cardService.findByCardId(Long.parseLong(filter.getValue())));
        } else {
            refresh();
        }
    }
}
