package com.kodilla.sportscentrefront.backend.connect.domain;

import com.kodilla.sportscentrefront.backend.connect.domain.enums.Goals;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    public User(Long userId) {
        this.userId = userId;
    }

    private Long userId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private LocalDate birthDate;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phone;
    @NotEmpty
    private Goals goal;
    private Boolean student;
    private Boolean gym;
    private Boolean swimmingPool;
    @NotEmpty
    private Card card;
    private Boolean autoExtension;
    private List<Invoice> invoices;
    @NotEmpty
    private LocalDate subValidity;
}
