package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.client.OrderClient;
import com.kodilla.sportscentrefront.backend.connect.client.SupplementsClient;
import com.kodilla.sportscentrefront.services.AdminOrderService;
import com.kodilla.sportscentrefront.view.admin.AdminOrderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;

public class OrderDeleteForm extends FormLayout {

    private final Label orderId = new Label();
    private final Label description = new Label();
    private final Label sum = new Label();

    private final AdminOrderView adminOrderView;

    private final AdminOrderService adminOrderService;

    private Order orderChosen = new Order();

    public OrderDeleteForm(AdminOrderView adminOrderView, OrderClient orderClient, SupplementsClient supplementsClient) {
        this.adminOrderService = AdminOrderService.getInstance(orderClient, supplementsClient);

        Button delete = new Button("Delete order");
        add(orderId, description, sum, delete);
        this.adminOrderView = adminOrderView;

        delete.addClickListener(event -> delete());
    }

    private void delete() {
        adminOrderService.delete(orderChosen.getOrderId());
        adminOrderView.refresh();
        setOrder(null);
        orderChosen = new Order();
        adminOrderView.refresh();
    }

    public void setOrder(Order order) {

        if (order == null) {
            setVisible(false);
        } else {
            setVisible(true);
            orderId.setText("Order Id: " + order.getOrderId());
            description.setText("Description: " + order.getDescription());
            sum.setText("Sum: " + order.getSum());

            orderChosen = order;
        }
    }
}
