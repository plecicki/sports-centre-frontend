package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.client.OrderClient;
import com.kodilla.sportscentrefront.backend.connect.client.SupplementsClient;
import com.kodilla.sportscentrefront.services.AdminOrderService;
import com.kodilla.sportscentrefront.view.admin.AdminOrderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.data.binder.Binder;

public class OrderDeleteForm extends FormLayout {

    private Label orderId = new Label();
    private Label description = new Label();
    private Label sum = new Label();

    private Button delete = new Button("Delete order");

    private AdminOrderView adminOrderView;

    private AdminOrderService adminOrderService;

    private Order orderChosen = new Order();

    public OrderDeleteForm(AdminOrderView adminOrderView, OrderClient orderClient, SupplementsClient supplementsClient) {
        this.adminOrderService = AdminOrderService.getInstance(orderClient, supplementsClient);

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
