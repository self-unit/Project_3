package com.codeclan.project.Project3.models;

public class Product {
    private String domain;
    private String name;
    private String url;
    private String img;
    private String price;
    private double rating;

    public Product(String domain, String name, String url, String img, String price, double rating) {
        this.domain = domain;
        this.name = name;
        this.url = url;
        this.img = img;
        this.price = price;
        this.rating = rating;
    }

    public String getDomain() {
        return domain;
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

    public String getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}
