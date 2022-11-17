package com.kodilla.sportscentrefront.services;

import com.kodilla.sportscentrefront.backend.connect.client.OrderClient;
import com.kodilla.sportscentrefront.backend.connect.client.SupplementsClient;
import com.kodilla.sportscentrefront.backend.connect.domain.Order;
import com.kodilla.sportscentrefront.backend.connect.domain.OrderDecInDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminOrderService {

    private final List<Order> orders;
    private static AdminOrderService adminOrderService;

    private final OrderClient orderClient;
    private final SupplementsClient supplementsClient;

    @Autowired
    private AdminOrderService(OrderClient orderClient, SupplementsClient supplementsClient) {
        this.orders = Arrays.asList(orderClient.getOrders());
        this.orderClient = orderClient;
        this.supplementsClient = supplementsClient;
    }

    public static AdminOrderService getInstance(OrderClient orderClient, SupplementsClient supplementsClient) {
        if (adminOrderService == null) {
            adminOrderService = new AdminOrderService(orderClient, supplementsClient);
        }
        return adminOrderService;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(Arrays.asList(orderClient.getOrders()));
    }

    public List<Order> findByUserId(Long userId) {
        return orders.stream()
                .filter(order -> order.getUser().getUserId().toString().contains(userId.toString()))
                .collect(Collectors.toList());
    }

    public Order makeOrder(OrderDecInDto orderDecInDto) {
        return supplementsClient.createOrder(orderDecInDto);
    }

    public void delete(Long orderId) {
        orderClient.deleteOrder(orderId);
    }
}
