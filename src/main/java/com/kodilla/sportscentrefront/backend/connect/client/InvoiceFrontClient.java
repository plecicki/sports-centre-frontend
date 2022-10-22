package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.Invoice;
import com.kodilla.sportscentrefront.backend.connect.domain.InvoiceEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class InvoiceFrontClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public void setPaymentPaidStatus(Long invoiceId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getStatus() + "/paid/" + invoiceId)
                .build()
                .encode()
                .toUri();
        restTemplate.put(
                uri, null
        );
    }

    public void setPaymentNotPaidStatus(Long invoiceId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getStatus() + "/not_paid/" + invoiceId)
                .build()
                .encode()
                .toUri();
        restTemplate.put(
                uri, null
        );
    }
}
