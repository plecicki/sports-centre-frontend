package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.client.InvoiceClient;
import com.kodilla.sportscentrefront.backend.connect.client.InvoiceFrontClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserClient;
import com.kodilla.sportscentrefront.backend.connect.domain.enums.PaymentStatus;
import com.kodilla.sportscentrefront.services.AdminInvoiceService;
import com.kodilla.sportscentrefront.view.admin.AdminInvoiceView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.data.binder.Binder;

import java.util.Arrays;
import java.util.List;

public class InvoiceForm extends FormLayout {

    private final Select<PaymentStatus> paymentStatusSelect = new Select<>();
    private final DatePicker paymentDeadline = new DatePicker("Payment deadline:");
    private final BigDecimalField sumField = new BigDecimalField("Sum of invoice");
    private final Select<Long> userIds = new Select<>();

    private final Button paid = new Button("Set PAID");
    private final Button notPaid = new Button("Set NOT PAID");

    private final Binder<Invoice> binder = new Binder<>(Invoice.class);

    private final AdminInvoiceView adminInvoiceView;

    private final AdminInvoiceService adminInvoiceService;

    public InvoiceForm(AdminInvoiceView adminInvoiceView, InvoiceClient invoiceClient, UserClient userClient,
                       InvoiceFrontClient invoiceFrontClient) {
        this.adminInvoiceService = AdminInvoiceService.getInstance(invoiceClient, invoiceFrontClient, userClient);

        paymentStatusSelect.setLabel("PaymentStatus:");
        paymentStatusSelect.setItems(PaymentStatus.values());
        paymentStatusSelect.setEmptySelectionAllowed(true);

        DatePicker.DatePickerI18n paymentDeadlinePicker = new DatePicker.DatePickerI18n();
        paymentDeadlinePicker.setDateFormat("yyyy-MM-dd");
        paymentDeadline.setI18n(paymentDeadlinePicker);
        paymentDeadline.setAllowedCharPattern("yyyy-MM-dd");

        //sum field

        userIds.setLabel("User Id:");
        userIds.setEmptySelectionAllowed(true);
        List<Long> usersIds = Arrays.stream(userClient.getUsers())
                .map(User::getUserId)
                .toList();
        userIds.setItems(usersIds);

        Button save = new Button("Save");
        Button delete = new Button("Delete");
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout paidButtons = new HorizontalLayout(paid, notPaid);

        add(paymentStatusSelect, paymentDeadline, sumField, userIds, buttons, paidButtons);
        this.adminInvoiceView = adminInvoiceView;

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

        binder.bindInstanceFields(this);
    }

    public void configurePayButtons(Boolean isEdited) {
        if (isEdited) {
            paid.setEnabled(true);
            notPaid.setEnabled(true);
            paid.addClickListener(event -> setPaid());
            notPaid.addClickListener(event -> setNotPaid());
        } else {
            paid.setEnabled(false);
            notPaid.setEnabled(false);
        }
    }

    private void save() {
        Invoice invoice = binder.getBean();

        if (invoice.getInvoiceId() == null) {
            invoice.setUser(new User(userIds.getValue()));
            InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                    paymentStatusSelect.getValue(),
                    paymentDeadline.getValue(),
                    sumField.getValue(),
                    invoice.getUser()
            );
            adminInvoiceService.createInvoice(invoiceCreateDto);
        } else {
            invoice.setUser(new User(userIds.getValue()));
            InvoiceEditDto invoiceEditDto = new InvoiceEditDto(
                    invoice.getInvoiceId(),
                    paymentStatusSelect.getValue(),
                    paymentDeadline.getValue(),
                    sumField.getValue(),
                    invoice.getUser()
            );
            adminInvoiceService.editInvoice(invoiceEditDto);
        }

        adminInvoiceView.refresh();
        setInvoice(null, false);
    }

    private void setPaid() {
        Invoice invoice = binder.getBean();
        try {
            adminInvoiceService.setPaidInvoice(invoice.getInvoiceId());
        } catch (NullPointerException e) {
            //It works in spite of exception
        }
        adminInvoiceView.refresh();
        setInvoice(null, false);
    }

    private void setNotPaid() {
        Invoice invoice = binder.getBean();
        try {
            adminInvoiceService.setNotPaidInvoice(invoice.getInvoiceId());
        } catch (NullPointerException e) {
            //It works in spite of exception
        }
        adminInvoiceView.refresh();
        setInvoice(null, false);
    }

    private void delete() {
        Invoice invoice = binder.getBean();
        adminInvoiceService.delete(invoice.getInvoiceId());
        adminInvoiceView.refresh();
        setInvoice(null, false);
    }

    public void setInvoice(Invoice invoice, Boolean createInvoice) {
        binder.setBean(invoice);

        if (invoice == null) {
            setVisible(false);
        } else {
            setVisible(true);

            if (!createInvoice) {
                paymentStatusSelect.setValue(invoice.getPaymentStatus());
                paymentDeadline.setValue(invoice.getPaymentDeadline());
                sumField.setValue(invoice.getSum());
                if (invoice.getUser() != null) {
                    userIds.setValue(invoice.getUser().getUserId());
                } else {
                    userIds.setValue(null);
                }
            } else {
                paymentStatusSelect.setValue(null);
                paymentDeadline.setValue(null);
                sumField.setValue(null);
                userIds.setValue(null);
            }
        }
    }
}
