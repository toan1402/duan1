package com.example.duan1;

public class Food {
    private String name;
    private String price;
    private String imageUrl;

    public Food(String name, String price, String imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getName() { return name; }
    public String getPrice() { return price; }
    public String getImageUrl() { return imageUrl; }
}
