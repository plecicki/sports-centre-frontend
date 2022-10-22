package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class InvoiceClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public Invoice[] getInvoices() {
        URI uri = getURIAddress();
        Invoice[] response = restTemplate.getForObject(
                uri, Invoice[].class
        );
        return response;
    }

    public Invoice getInvoice(Long invoiceId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getInvoice() + "/" + invoiceId)
                .build()
                .encode()
                .toUri();
        Invoice response = restTemplate.getForObject(
                uri, Invoice.class
        );
        return response;
    }

    public Invoice createInvoice(InvoiceCreateDto invoiceCreateDto) {
        URI uri = getURIAddress();
        Invoice response = restTemplate.postForObject(
                uri, invoiceCreateDto, Invoice.class
        );

        return response;
    }

    public Invoice editInvoice(InvoiceEditDto invoiceEditDto) {
        URI uri = getURIAddress();
        restTemplate.put(
                uri, invoiceEditDto
        );

        Invoice response = new Invoice(
                invoiceEditDto.getInvoiceId(),
                invoiceEditDto.getPaymentStatus(),
                invoiceEditDto.getPaymentDeadline(),
                invoiceEditDto.getSum(),
                invoiceEditDto.getUser()
        );
        return response;
    }

    public void deleteInvoice(Long invoiceId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getInvoice() + "/" + invoiceId)
                .build()
                .encode()
                .toUri();
        restTemplate.delete(uri);
    }

    private URI getURIAddress() {
        return UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getInvoice())
                .build()
                .encode()
                .toUri();
    }
}
