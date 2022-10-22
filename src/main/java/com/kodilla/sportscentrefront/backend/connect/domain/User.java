package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.Goals;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public User(Long userId) {
        this.userId = userId;
    }

    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private Goals goal;
    private Boolean student;
    private Boolean gym;
    private Boolean swimmingPool;
    private Card card;
    private Boolean autoExtension;
    private List<Invoice> invoices;
    private LocalDate subValidity;
}
