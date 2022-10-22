package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public String createAccount(AccountCreateDto accountCreateDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getAccount())
                .build()
                .encode()
                .toUri();
        String exception = "Success";
        exception = restTemplate.postForObject(
                uri, accountCreateDto, String.class
        );
        return exception;
    }

    //If error String else User
    public Object login(AccountInDto accountInDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getAccount() + "/login")
                .build()
                .encode()
                .toUri();;
        Object response;
        try {
            AccountOutDto accountOutDto = restTemplate.postForObject(
                    uri, accountInDto, AccountOutDto.class
            );
            response = accountOutDto.getUser();
        } catch (RestClientException e){
            response = restTemplate.postForObject(
                    uri, accountInDto, String.class
            );
        }
        return response;
    }
}
