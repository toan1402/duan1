package com.example.duan1;


import com.example.duan1.Product;

import java.util.ArrayList;
import java.util.List;


public class CartManager {
    private static List<Product> cartItems = new ArrayList<>();

    public static void addToCart(Product product) {
        cartItems.add(product);
    }

    public static void removeFromCart(Product product) {
        cartItems.remove(product);
    }

    public static List<Product> getCartItems() {
        return new ArrayList<>(cartItems); // Trả bản sao để tránh lỗi thao tác trực tiếp
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
