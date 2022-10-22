package com.kodilla.sportscentrefront.backend.connect.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyYouTubeDto {
    private LocalDateTime publishedAt;
    private String title;
    private String channelTitle;
    private URI imageUrl;
    private String viewCount;
    private String likeCount;
    private URI videoUrl;
}
