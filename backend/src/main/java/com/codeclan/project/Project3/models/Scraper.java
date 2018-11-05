package com.codeclan.project.Project3.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Scraper {
    private String productName;
    private String ASIN;

    public Scraper(String productName) {
        this.productName = productName;
        this.ASIN = null;
    }

    public void getASIN() throws IOException {
        String searchPage = "https://www.amazon.co.uk/s/field-keywords=" + this.productName;
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        int nResult = 0;
        boolean found = false;
        while(found == false){
            Element curProduct = doc.select("#result_" + nResult).first();
            if(!curProduct.html().contains("sponsored")) {
                found = true;
            } else {
                nResult++;
            }
        }

        Element product = doc.select("#result_" + nResult).first();
        Element elASIN = product.select("[data-asin]").first();

        System.out.println(elASIN.attr("data-asin"));
        this.ASIN = elASIN.attr("data-asin");
    }



}

