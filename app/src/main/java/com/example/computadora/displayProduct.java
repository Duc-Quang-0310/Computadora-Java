package com.example.computadora;

public class displayProduct {
    private String price;
    private String imageUrl;
    private Boolean AddToWishList;
    private Boolean AddToCart;

    public displayProduct(String price, String imageUrl) {
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getAddToWishList() {
        return AddToWishList;
    }

    public Boolean getAddToCart() {
        return AddToCart;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAddToWishList(Boolean addToWishList) {
        AddToWishList = addToWishList;
    }

    public void setAddToCart(Boolean addToCart) {
        AddToCart = addToCart;
    }

    @Override
    public String toString() {
        return "displayProduct{" +
                "price='" + price + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", AddToWishList=" + AddToWishList +
                ", AddToCart=" + AddToCart +
                '}';
    }
}
