package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountInDto;
import com.kodilla.sportscentrefront.backend.connect.domain.AccountOutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class AccountClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;
    private final UserCardClient userCardClient;
    private final CardClient cardClient;

    public String createAccount(AccountCreateDto accountCreateDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getAccount())
                .build()
                .encode()
                .toUri();
        String exception = restTemplate.postForObject(
                uri, accountCreateDto, String.class
        );
        if (exception == null) {
            exception = "Success";
        } else if (exception.contains("permission")) {
            Long userId = accountCreateDto.getUser().getUserId();
            Long cardId = accountCreateDto.getUser().getCard().getCardId();

            userCardClient.deleteUser(userId);
            cardClient.deleteCard(cardId);
        }
        return exception;
    }

    //If error String else User
    public Object login(AccountInDto accountInDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getAccount() + "/login")
                .build()
                .encode()
                .toUri();
        Object response;
        try {
            response = restTemplate.postForObject(
                    uri, accountInDto, AccountOutDto.class
            );
        } catch (RestClientException e){
            response = restTemplate.postForObject(
                    uri, accountInDto, String.class
            );
        }
        return response;
    }

    public Boolean checkIfAccountExistsByUsername(String username) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getAccount() +
                        "/" + username)
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(
                uri, Boolean.class
        );
    }
}
