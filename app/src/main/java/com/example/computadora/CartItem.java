package com.example.computadora;

public class CartItem {
    private String name;
    private String price;
    private Integer quantity;
    private String imageUrl;

    public CartItem(String name, String price, Integer quantity, String imageUrl) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
