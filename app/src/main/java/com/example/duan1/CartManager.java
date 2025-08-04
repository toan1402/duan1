package com.example.duan1;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<CartItem> cartItems = new ArrayList<>();

    public static void addToCart(CartItem item) {
        // Kiểm tra nếu món đã có thì tăng số lượng
        for (CartItem existingItem : cartItems) {
            if (existingItem.getName().equals(item.getName())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        // Nếu chưa có thì thêm mới
        cartItems.add(item);
    }

    public static void removeFromCart(String name) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getName().equals(name)) {
                cartItems.remove(i);
                return;
            }
        }
    }

    public static void clearCart() {
        cartItems.clear();
    }

    public static List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems); // Trả về bản sao để tránh lỗi ngoài ý muốn
    }

    public static int getTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
