package com.example.duan1;

import java.util.ArrayList;
import java.util.List;

public class FavoriteManager {
    private static final List<Product> favoriteList = new ArrayList<>();

    public static void add(Product product) {
        if (!favoriteList.contains(product)) {
            favoriteList.add(product);
        }
    }

    public static void remove(Product product) {
        favoriteList.remove(product);
    }

    public static boolean isFavorite(Product product) {
        return favoriteList.contains(product);
    }

    public static List<Product> getFavorites() {
        return new ArrayList<>(favoriteList); // Trả về bản sao để tránh lỗi khi thao tác
    }
}
