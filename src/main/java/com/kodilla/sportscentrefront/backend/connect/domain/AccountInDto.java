package com.kodilla.sportscentrefront.backend.connect.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountInDto {
    private String username;
    private String password;
}
