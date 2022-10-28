package com.kodilla.sportscentrefront.view.user;

import com.kodilla.sportscentrefront.backend.connect.client.InvoiceFrontClient;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountOutDto;
import com.kodilla.sportscentrefront.backend.connect.domain.Invoice;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@PageTitle("User Invoices")
public class UserInvoiceView extends VerticalLayout {

    @Autowired
    public UserInvoiceView(InvoiceFrontClient invoiceFrontClient) {
        User user = VaadinSession.getCurrent().getAttribute(AccountOutDto.class).getUser();
        Invoice[] invoicesArray = invoiceFrontClient.getInvoiceByUserId(user.getUserId());
        List<Invoice> invoiceList = Arrays.asList(invoicesArray);

        if (invoiceList.size() > 0) {
            Grid<Invoice> grid = new Grid<>(Invoice.class);
            grid.setColumns("invoiceId", "paymentStatus", "paymentDeadline", "sum");
            grid.addColumn(invoice -> invoice.getUser().getUserId()).setHeader("User Id");
            grid.setItems(invoiceList);
            add(grid);
        } else {
            Label warningLabel = new Label("You do not have invoices to show");
            warningLabel.getStyle().set("font-size", "28px");
            warningLabel.getStyle().set("font-weight", "bold");
            add(warningLabel);
        }
    }
}
