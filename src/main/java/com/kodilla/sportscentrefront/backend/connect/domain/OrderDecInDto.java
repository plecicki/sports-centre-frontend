package com.kodilla.sportscentrefront.backend.connect.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDecInDto {

    private Boolean bcaa;
    private Boolean caffeine;
    private Boolean citrulline;
    private Boolean creatine;
    private Boolean protein;

    private User user;
}
