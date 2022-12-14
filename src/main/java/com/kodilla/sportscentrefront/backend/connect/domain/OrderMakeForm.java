package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.client.OrderClient;
import com.kodilla.sportscentrefront.backend.connect.client.SupplementsClient;
import com.kodilla.sportscentrefront.services.AdminOrderService;
import com.kodilla.sportscentrefront.view.admin.AdminOrderView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.server.VaadinSession;

public class OrderMakeForm extends FormLayout {

    private final Checkbox bcaaCB = new Checkbox("BCAA:");
    private final Checkbox caffeineCB = new Checkbox("CAFFEINE:");
    private final Checkbox citrullineCB = new Checkbox("CITRULLINE:");
    private final Checkbox creatineCB = new Checkbox("CREATINE:");
    private final Checkbox proteinCB = new Checkbox("PROTEIN:");

    private final AdminOrderView adminOrderView;

    private final AdminOrderService adminOrderService;

    public OrderMakeForm(AdminOrderView adminOrderView, OrderClient orderClient, SupplementsClient supplementsClient) {
        this.adminOrderService = AdminOrderService.getInstance(orderClient, supplementsClient);

        Button makeOrder = new Button("Make order");
        add(bcaaCB, caffeineCB, citrullineCB, creatineCB, proteinCB, makeOrder);
        this.adminOrderView = adminOrderView;

        makeOrder.addClickListener(event -> makeOrder());
    }

    private void makeOrder() {

        User user = VaadinSession.getCurrent().getAttribute(AccountOutDto.class).getUser();
        OrderDecInDto orderDecInDto = new OrderDecInDto(
                bcaaCB.getValue(),
                caffeineCB.getValue(),
                citrullineCB.getValue(),
                creatineCB.getValue(),
                proteinCB.getValue(),
                user
        );

        Order order = adminOrderService.makeOrder(orderDecInDto);
        setVisible(false);
        adminOrderView.showNotification("The order got created :-) You ordered: " + order.getDescription() +
                " :-) Cost: " + order.getSum());
        adminOrderView.refresh();
    }

    public void setOrder(OrderDecInDto orderDecInDto) {

        if (orderDecInDto == null) {
            setVisible(false);
        } else {
            setVisible(true);

            bcaaCB.setValue(false);
            caffeineCB.setValue(false);
            citrullineCB.setValue(false);
            creatineCB.setValue(false);
            proteinCB.setValue(false);
        }
    }
}
