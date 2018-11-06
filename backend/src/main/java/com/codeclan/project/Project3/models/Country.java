package com.codeclan.project.Project3.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class Country extends ResourceSupport {

    private String countryCode;
    private String price;
    private String link;

    @JsonCreator
    public Country(@JsonProperty("country_code") String countryCode, @JsonProperty("price") String price, @JsonProperty("link") String link) {
        this.countryCode = countryCode;
        this.price = price;
        this.link = link;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
