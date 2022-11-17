package com.kodilla.sportscentrefront.view.admin;

import com.kodilla.sportscentrefront.backend.connect.client.InvoiceClient;
import com.kodilla.sportscentrefront.backend.connect.client.InvoiceFrontClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserClient;
import com.kodilla.sportscentrefront.backend.connect.domain.Invoice;
import com.kodilla.sportscentrefront.backend.connect.domain.InvoiceForm;
import com.kodilla.sportscentrefront.services.AdminInvoiceService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Admin Invoice")
public class AdminInvoiceView extends VerticalLayout {

    private final AdminInvoiceService invoiceService;
    private final Grid<Invoice> grid = new Grid<>(Invoice.class);
    private final TextField filter = new TextField();
    private final InvoiceForm form;

    public AdminInvoiceView(InvoiceClient invoiceClient, UserClient userClient, InvoiceFrontClient invoiceFrontClient) {
        form = new InvoiceForm(this, invoiceClient, userClient, invoiceFrontClient);

        invoiceService = AdminInvoiceService.getInstance(invoiceClient, invoiceFrontClient, userClient);

        filter.setPlaceholder("Filter invoices by user id...");
        filter.setClearButtonVisible(true);
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> update());
        grid.setColumns("invoiceId", "paymentStatus", "paymentDeadline", "sum");
        grid.addColumn(invoice -> {
            if (invoice.getUser() == null) {
                return "Lack of user";
            } else {
                return invoice.getUser().getUserId();
            }
        }).setHeader("User Id");

        Button addNewInvoice = new Button("Add new invoice");
        addNewInvoice.addClickListener(event -> {
            grid.asSingleSelect().clear();
            form.setInvoice(new Invoice(), true);
            form.configurePayButtons(false);
        });
        HorizontalLayout toolbar = new HorizontalLayout(filter, addNewInvoice);

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        form.setInvoice(null, false);
        setSizeFull();
        refresh();

        grid.asSingleSelect().addValueChangeListener(event -> {
            form.setInvoice(grid.asSingleSelect().getValue(), false);
            form.setEnabled(true);
            form.configurePayButtons(true);
        });
    }

    public void refresh() {
        grid.setItems(invoiceService.getInvoices());
    }

    private void update() {
        if (!filter.getValue().isEmpty()) {
            grid.setItems(invoiceService.findByUserId(Long.parseLong(filter.getValue())));
        } else {
            refresh();
        }
    }
}
