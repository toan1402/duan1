package com.example.duan1;

import java.util.List;

public class Order {
    private List<CartItem> items;
    private long timestamp;
    private String customerName;
    private String phoneNumber;
    private String address;

    public Order(String customerName, String phoneNumber, String address,
                 List<CartItem> items, long timestamp) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.items = items;
        this.timestamp = timestamp;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
