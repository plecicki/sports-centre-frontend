package com.kodilla.sportscentrefront.services;

import com.kodilla.sportscentrefront.backend.connect.client.UserCardClient;
import com.kodilla.sportscentrefront.backend.connect.client.UserClient;
import com.kodilla.sportscentrefront.backend.connect.domain.User;
import com.kodilla.sportscentrefront.backend.connect.domain.UserCreateDto;
import com.kodilla.sportscentrefront.backend.connect.domain.UserEditDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService {

    private final List<User> users;
    private static AdminUserService adminUserService;

    private final UserClient userClient;
    private final UserCardClient userCardClient;

    @Autowired
    private AdminUserService(UserClient userClient, UserCardClient userCardClient) {
        this.users = Arrays.asList(userClient.getUsers());
        this.userClient = userClient;
        this.userCardClient = userCardClient;
    }

    public static AdminUserService getInstance(UserClient userClient, UserCardClient userCardClient) {
        if (adminUserService == null) {
            adminUserService = new AdminUserService(userClient, userCardClient);
        }
        return adminUserService;
    }

    public List<User> getUsers() {
        return new ArrayList<>(Arrays.asList(userClient.getUsers()));
    }

    public List<User> findByUserId(Long userId) {
        return users.stream()
                .filter(user -> user.getUserId().toString().contains(userId.toString()))
                .collect(Collectors.toList());
    }

    public void createUser(UserCreateDto userCreateDto) {
        userCardClient.createUser(userCreateDto);
    }

    public void editUser(UserEditDto userEditDto) {
        userCardClient.editUser(userEditDto);
    }

    public void delete(Long userId) {
        userCardClient.deleteUser(userId);
    }
}
