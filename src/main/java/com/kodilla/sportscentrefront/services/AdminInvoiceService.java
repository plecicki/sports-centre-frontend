package com.kodilla.sportscentrefront.services;

import com.kodilla.sportscentrefront.backend.connect.client.InvoiceClient;
import com.kodilla.sportscentrefront.backend.connect.client.InvoiceFrontClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserClient;
import com.kodilla.sportscentrefront.backend.connect.domain.Invoice;
import com.kodilla.sportscentrefront.backend.connect.domain.InvoiceCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.InvoiceEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminInvoiceService {

    private final List<Invoice> invoices;
    private static AdminInvoiceService adminInvoiceService;

    private final InvoiceClient invoiceClient;
    private final InvoiceFrontClient invoiceFrontClient;

    @Autowired
    private AdminInvoiceService(InvoiceClient invoiceClient, InvoiceFrontClient invoiceFrontClient, UserClient userClient) {
        this.invoices = Arrays.asList(invoiceClient.getInvoices());
        this.invoiceClient = invoiceClient;
        this.invoiceFrontClient = invoiceFrontClient;
    }

    public static AdminInvoiceService getInstance(InvoiceClient invoiceClient, InvoiceFrontClient invoiceFrontClient,
                                                  UserClient userClient) {
        if (adminInvoiceService == null) {
            adminInvoiceService = new AdminInvoiceService(invoiceClient, invoiceFrontClient, userClient);
        }
        return adminInvoiceService;
    }

    public List<Invoice> getInvoices() {
        return new ArrayList<>(Arrays.asList(invoiceClient.getInvoices()));
    }

    public List<Invoice> findByUserId(Long userId) {
        return invoices.stream()
                .filter(invoice -> invoice.getUser().getUserId().toString().contains(userId.toString()))
                .collect(Collectors.toList());
    }

    public void createInvoice(InvoiceCreateDto invoiceCreateDto) {
        invoiceClient.createInvoice(invoiceCreateDto);
    }

    public void editInvoice(InvoiceEditDto invoiceEditDto) {
        invoiceClient.editInvoice(invoiceEditDto);
    }

    public void setPaidInvoice(Long invoiceId) {
        invoiceFrontClient.setPaymentPaidStatus(invoiceId);
    }

    public void setNotPaidInvoice(Long invoiceId) {
        invoiceFrontClient.setPaymentNotPaidStatus(invoiceId);
    }

    public void delete(Long invoiceId) {
        invoiceClient.deleteInvoice(invoiceId);
    }
}
