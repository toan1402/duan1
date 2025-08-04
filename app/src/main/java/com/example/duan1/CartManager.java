package com.example.duan1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartManager {
    private static final Map<Product, Integer> cartMap = new HashMap<>();

    public static void addToCart(Product product) {
        int currentQty = cartMap.getOrDefault(product, 0);
        cartMap.put(product, currentQty + 1);
    }

    public static void updateQuantity(Product product, int quantity) {
        if (quantity <= 0) {
            cartMap.remove(product);
        } else {
            cartMap.put(product, quantity);
        }
    }

    public static void removeFromCart(Product product) {
        cartMap.remove(product);
    }

    public static Map<Product, Integer> getCartMap() {
        return new HashMap<>(cartMap);
    }

    public static List<Product> getCartItems() {
        return new ArrayList<>(cartMap.keySet());
    }

    public static int getQuantity(Product product) {
        return cartMap.getOrDefault(product, 0);
    }

    public static void clearCart() {
        cartMap.clear();
    }

    public static int getTotalPrice() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
