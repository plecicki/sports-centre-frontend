package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.kodilla.sportscentrefront.backend.connect.domain.UserCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.UserEditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class UserCardClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public User createUser(UserCreateDto userCreateDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getUsercard())
                .build()
                .encode()
                .toUri();
        User response = restTemplate.postForObject(
                uri, userCreateDto, User.class
        );

        return response;
    }

    public User editUser(UserEditDto userEditDto) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getUsercard())
                .build()
                .encode()
                .toUri();

        restTemplate.put(
                uri, userEditDto
        );

        User response = new User(
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
        return response;
    }

    public void deleteUser(Long userId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getUsercard() + "/" + userId)
                .build()
                .encode()
                .toUri();
        restTemplate.delete(uri);
    }
}
