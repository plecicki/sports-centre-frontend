package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountOutDto {
    private String username;
    private Role role;
    private User user;
}
