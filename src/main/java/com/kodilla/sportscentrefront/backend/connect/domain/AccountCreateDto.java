package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateDto {

    private String username;
    private String password;
    private Role role;
    private User user;

    private String createAdminKey;
}
