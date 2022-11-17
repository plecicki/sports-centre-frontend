package com.kodilla.sportscentrefront.backend.connect.domain;

import com.vaadin.flow.component.html.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TableYouTubeDto {
    private LocalDate publishedAt;
    private String title;
    private String channelTitle;
    private Image image;
    private String viewCount;
    private String likeCount;
    private String videoUrl;
}
