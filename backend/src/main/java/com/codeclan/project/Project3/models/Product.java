package com.codeclan.project.Project3.models;

public class Product {
    private String name;
    private String url;
    private String img;
    private double price;
    private double rating;

    public Product(String name, String url, String img, double price, double rating) {
        this.name = name;
        this.url = url;
        this.img = img;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImg() {
        return img;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}
