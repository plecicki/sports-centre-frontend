package com.kodilla.sportscentrefront.view.user;

import com.kodilla.sportscentrefront.backend.connect.client.SupplementsClient;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountOutDto;
import com.kodilla.sportscentrefront.backend.connect.domain.Order;
import com.kodilla.sportscentrefront.backend.connect.domain.OrderDecInDto;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("User Order")
@CssImport("./styles/views/login/login-view.css")
public class UserOrderView extends VerticalLayout {

    private User user;

    private H1 orderMakeHeading = new H1("Make an order:");

    private Checkbox bcaaCB = new Checkbox("BCAA");
    private Checkbox caffeineCB = new Checkbox("Caffeine");
    private Checkbox citrullineCB = new Checkbox("Citrulline");
    private Checkbox creatineCB = new Checkbox("Creatine");
    private Checkbox proteinCB = new Checkbox("Protein");

    private Button orderButton = new Button("Order");

    private H1 orderCreatedHeading = new H1("Order created");

    private FormLayout formLabels = new FormLayout();

    private Label descriptionLabel = new Label();
    private Label sumLabel = new Label();

    private Button okButton = new Button("OK");

    public UserOrderView(SupplementsClient supplementsClient) {
        setId("login-view");

        User user = VaadinSession.getCurrent().getAttribute(AccountOutDto.class).getUser();

        orderCreatedHeading.setVisible(false);

        formLabels.add(descriptionLabel, sumLabel);
        formLabels.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1)
        );

        formLabels.setVisible(false);
        okButton.setVisible(false);

        orderButton.setEnabled(false);

        bcaaCB.addValueChangeListener(event -> checkOrderButton());
        caffeineCB.addValueChangeListener(event -> checkOrderButton());
        citrullineCB.addValueChangeListener(event -> checkOrderButton());
        creatineCB.addValueChangeListener(event -> checkOrderButton());
        proteinCB.addValueChangeListener(event -> checkOrderButton());

        add(orderMakeHeading, bcaaCB, caffeineCB, citrullineCB, creatineCB, proteinCB, orderButton);
        add(orderCreatedHeading, formLabels, okButton);

        orderButton.addClickListener(event -> {
            OrderDecInDto orderDecInDto = new OrderDecInDto(
                    bcaaCB.getValue(),
                    caffeineCB.getValue(),
                    citrullineCB.getValue(),
                    creatineCB.getValue(),
                    proteinCB.getValue(),
                    user
            );

            orderMakeHeading.setVisible(false);
            bcaaCB.setVisible(false);
            caffeineCB.setVisible(false);
            citrullineCB.setVisible(false);
            creatineCB.setVisible(false);
            proteinCB.setVisible(false);
            orderButton.setVisible(false);

            Order order = supplementsClient.createOrder(orderDecInDto);

            descriptionLabel.setText("You ordered:" + order.getDescription());
            sumLabel.setText("Sum: " + order.getSum());

            orderCreatedHeading.setVisible(true);
            formLabels.setVisible(true);
            okButton.setVisible(true);
        });

        okButton.addClickListener(event -> {
            orderMakeHeading.setVisible(true);
            bcaaCB.setVisible(true);
            caffeineCB.setVisible(true);
            citrullineCB.setVisible(true);
            creatineCB.setVisible(true);
            proteinCB.setVisible(true);
            orderButton.setVisible(true);

            bcaaCB.setValue(false);
            caffeineCB.setValue(false);
            citrullineCB.setValue(false);
            creatineCB.setValue(false);
            proteinCB.setValue(false);

            orderCreatedHeading.setVisible(false);
            formLabels.setVisible(false);
            okButton.setVisible(false);

            checkOrderButton();
        });
    }

    private void checkOrderButton() {
        if (bcaaCB.getValue() ||
        caffeineCB.getValue() ||
        citrullineCB.getValue() ||
        creatineCB.getValue() ||
        proteinCB.getValue()) {
            orderButton.setEnabled(true);
        } else {
            orderButton.setEnabled(false);
        }
    }
}
