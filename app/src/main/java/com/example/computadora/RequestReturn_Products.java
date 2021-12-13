package com.example.computadora;

import java.util.ArrayList;

public class RequestReturn_Products {
    private ArrayList<String> review;
    private ArrayList<String> imgs;
    private String _id;
    private String name;
    private String chip;
    private String screen;
    private String color;
    private String ram;
    private String card;
    private String storage;
    private String pin;
    private String connection;
    private String weight;
    private String window;
    private String price;
    private Boolean AddToWishList;
    private Boolean AddToCart;


    public RequestReturn_Products(ArrayList<String> review, ArrayList<String> imgs, String _id, String name, String chip, String screen, String color, String ram, String card, String storage, String pin, String connection, String weight, String window, String price) {
        this.review = review;
        this.imgs = imgs;
        this._id = _id;
        this.name = name;
        this.chip = chip;
        this.screen = screen;
        this.color = color;
        this.ram = ram;
        this.card = card;
        this.storage = storage;
        this.pin = pin;
        this.connection = connection;
        this.weight = weight;
        this.window = window;
        this.price = price;
    }

    public RequestReturn_Products(Boolean addToWishList, Boolean addToCart) {
        AddToWishList = addToWishList;
        AddToCart = addToCart;
    }

    public ArrayList<String> getReview() {
        return review;
    }

    public void setReview(ArrayList<String> review) {
        this.review = review;
    }

    public ArrayList<String> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RequestReturn_Products{" +
                "review=" + review +
                ", imgs=" + imgs +
                ", _id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", chip='" + chip + '\'' +
                ", screen='" + screen + '\'' +
                ", color='" + color + '\'' +
                ", ram='" + ram + '\'' +
                ", card='" + card + '\'' +
                ", storage='" + storage + '\'' +
                ", pin='" + pin + '\'' +
                ", connection='" + connection + '\'' +
                ", weight='" + weight + '\'' +
                ", window='" + window + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
