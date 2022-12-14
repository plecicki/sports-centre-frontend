package com.kodilla.sportscentrefront.view.admin;

import com.kodilla.sportscentrefront.backend.connect.client.CardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserClient;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.kodilla.sportscentrefront.backend.connect.domain.UserForm;
import com.kodilla.sportscentrefront.services.AdminUserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;


@PageTitle("Admin User")
public class AdminUserView extends VerticalLayout {

    private final AdminUserService userService;
    private final Grid<User> grid = new Grid<>(User.class);
    private final TextField filter = new TextField();
    private final UserForm form;

    public AdminUserView(UserClient userClient, UserCardClient userCardClient, CardClient cardClient) {
        form = new UserForm(this, userClient, userCardClient, cardClient);

        userService = AdminUserService.getInstance(userClient, userCardClient);

        filter.setPlaceholder("Filter by id...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("userId", "name", "surname", "birthDate", "email", "phone", "goal", "student",
        "gym", "swimmingPool", "autoExtension", "subValidity");
        grid.addColumn(user -> {
            if (user.getCard() == null) {
                return "Lack of card";
            } else {
                return user.getCard().getCardId();
            }
        }).setHeader("Card Id");

        Button addNewUser = new Button("Add new user");
        addNewUser.addClickListener(event -> {
            grid.asSingleSelect().clear();
            form.setUser(new User(), true, userClient, cardClient);
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewUser);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setUser(null, false, userClient, cardClient);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> {
            form.setUser(grid.asSingleSelect().getValue(), false, userClient, cardClient);
            form.setEnabled(true);
        });
    }

    public void refresh() {
        grid.setItems(userService.getUsers());
    }

    private void update() {
        if (!filter.getValue().isEmpty()) {
            grid.setItems(userService.findByUserId(Long.parseLong(filter.getValue())));
        } else {
            refresh();
        }
    }
}
