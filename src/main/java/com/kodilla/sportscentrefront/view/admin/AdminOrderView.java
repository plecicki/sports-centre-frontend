package com.kodilla.sportscentrefront.view.admin;

import com.kodilla.sportscentrefront.backend.connect.client.OrderClient;
import com.kodilla.sportscentrefront.backend.connect.client.SupplementsClient;
import com.kodilla.sportscentrefront.backend.connect.domain.Order;
import com.kodilla.sportscentrefront.backend.connect.domain.OrderDecInDto;
import com.kodilla.sportscentrefront.backend.connect.domain.OrderDeleteForm;
import com.kodilla.sportscentrefront.backend.connect.domain.OrderMakeForm;
import com.kodilla.sportscentrefront.services.AdminOrderService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Admin Order")
public class AdminOrderView extends VerticalLayout {

    private final AdminOrderService orderService;
    private final Grid<Order> grid = new Grid<>(Order.class);
    private final TextField filter = new TextField();

    private final OrderMakeForm orderMakeForm;
    private final OrderDeleteForm orderDeleteForm;

    public AdminOrderView(OrderClient orderClient, SupplementsClient supplementsClient) {
        orderMakeForm = new OrderMakeForm(this, orderClient, supplementsClient);
        orderDeleteForm = new OrderDeleteForm(this, orderClient, supplementsClient);

        orderService = AdminOrderService.getInstance(orderClient, supplementsClient);

        filter.setPlaceholder("Filter orders by user id...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("orderId", "description", "sum");
        grid.addColumn(order -> {
            if (order.getUser() == null) {
                return "Lack of user";
            } else {
                return order.getUser().getUserId();
            }
        }).setHeader("User Id");

        Button createOrder = new Button("Create order");
        createOrder.addClickListener(event -> {
            grid.asSingleSelect().clear();
            orderMakeForm.setOrder(new OrderDecInDto());
            orderDeleteForm.setVisible(false);
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, createOrder);

        HorizontalLayout mainContent = new HorizontalLayout(grid, orderMakeForm, orderDeleteForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        orderMakeForm.setOrder(null);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> {
            orderDeleteForm.setOrder(grid.asSingleSelect().getValue());
            orderDeleteForm.setEnabled(true);
            orderMakeForm.setVisible(false);
        });
    }

    public void refresh() {
        grid.setItems(orderService.getOrders());
    }

    private void update() {
        if (!filter.getValue().isEmpty()) {
            grid.setItems(orderService.findByUserId(Long.parseLong(filter.getValue())));
        } else {
            refresh();
        }
    }

    public void showNotification(String text) {
        Notification.show(text);
    }
}
