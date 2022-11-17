package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public User[] getUsers() {
        URI uri = getURIAddress();
        return restTemplate.getForObject(
                uri, User[].class
        );
    }

    public User getUser(Long userId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getUser() + "/" + userId)
                .build()
                .encode()
                .toUri();
        return restTemplate.getForObject(
                uri, User.class
        );
    }

    public User createUser(UserCreateDto userCreateDto) {
        URI uri = getURIAddress();

        return restTemplate.postForObject(
                uri, userCreateDto, User.class
        );
    }

    public User editUser(UserEditDto userEditDto) {
        URI uri = getURIAddress();
        restTemplate.put(
                uri, userEditDto
        );

        return new User(
                userEditDto.getUserId(),
                userEditDto.getName(),
                userEditDto.getSurname(),
                userEditDto.getBirthDate(),
                userEditDto.getEmail(),
                userEditDto.getPhone(),
                userEditDto.getGoal(),
                userEditDto.getStudent(),
                userEditDto.getGym(),
                userEditDto.getSwimmingPool(),
                userEditDto.getCard(),
                userEditDto.getAutoExtension(),
                new ArrayList<>(),
                userEditDto.getSubValidity()
        );
    }

    public void deleteUser(Long userId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getUser() + "/" + userId)
                .build()
                .encode()
                .toUri();
        restTemplate.delete(uri);
    }

    private URI getURIAddress() {
        return UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getUser())
                .build()
                .encode()
                .toUri();
    }
}
