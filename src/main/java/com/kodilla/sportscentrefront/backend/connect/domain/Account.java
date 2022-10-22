package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    public Account(Long accountId) {
        this.accountId = accountId;
    }

    private Long accountId;
    private String username;
    private String passwordSalt;
    private String passwordHash;
    private Role role;
    private User user;
}
