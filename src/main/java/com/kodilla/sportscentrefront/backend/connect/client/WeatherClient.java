package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.TomWeatherDto;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public TomWeatherDto getWeather() {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getWeather())
                .build()
                .encode()
                .toUri();
        TomWeatherDto response = restTemplate.getForObject(
                uri, TomWeatherDto.class
        );
        return response;
    }
}
