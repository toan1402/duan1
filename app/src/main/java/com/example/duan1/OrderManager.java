package com.example.duan1;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final List<Order> orderHistory = new ArrayList<>();

    public static void addOrder(Order order) {
        orderHistory.add(order);
    }

    public static List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory); // trả về bản sao
    }

    public static void clearHistory() {
        orderHistory.clear();
    }
}
