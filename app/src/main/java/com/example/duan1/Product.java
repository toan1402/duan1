package com.example.duan1;

public class Product {
    private String name;
    private String category;
    private int imageResId;
    private int price;
    private boolean isFavorite;

    public Product(String name, String category, int imageResId, int price) {
        this.name = name;
        this.category = category;
        this.imageResId = imageResId;
        this.price = price;
        this.isFavorite = false;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getPrice() {
        return price;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    // Setters
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // Override equals() để so sánh nội dung sản phẩm
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;

        return imageResId == product.imageResId &&
                price == product.price &&
                name.equals(product.name) &&
                category.equals(product.category);
    }

    // Override hashCode() để đảm bảo đồng bộ với equals()
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + imageResId;
        result = 31 * result + price;
        return result;
    }
}
