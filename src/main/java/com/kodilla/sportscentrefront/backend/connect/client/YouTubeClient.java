package com.kodilla.sportscentrefront.backend.connect.client;

import com.kodilla.sportscentrefront.backend.connect.config.BackEndConfig;
import com.kodilla.sportscentrefront.backend.connect.domain.MyYouTubeDto;
import com.kodilla.sportscentrefront.backend.connect.domain.TomWeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class YouTubeClient {

    private final RestTemplate restTemplate;
    private final BackEndConfig backEndConfig;

    public MyYouTubeDto[] getYouTube() {
        URI uri = UriComponentsBuilder.fromHttpUrl(backEndConfig.getEndpoint() + backEndConfig.getYoutube())
                .build()
                .encode()
                .toUri();
        MyYouTubeDto[] response = restTemplate.getForObject(
                uri, MyYouTubeDto[].class
        );
        return response;
    }
}
