package com.codeclan.project.Project3.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public class Scraper {
    private String productName;
    private int resultIndex;
    private String ASIN;

    public Scraper(String productName) {
        this.productName = productName;
        this.ASIN = null;
    }

    public void getASIN() throws IOException {
        String searchPage = "https://www.amazon.co.uk/s/field-keywords=" + this.productName;
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        if(!html.contains("did not match any products")) {
            int iResult = 0;
            boolean found = false;
            while (found == false) {
                Element curProduct = doc.select("#result_" + iResult).first();
                if (!curProduct.html().contains("sponsored")) {
                    this.resultIndex = iResult;
                    found = true;
                } else {
                    iResult++;
                }
            }

            Element product = doc.select("#result_" + iResult).first();
            Element elASIN = product.select("[data-asin]").first();

            this.ASIN = elASIN.attr("data-asin");
        }
    }

    public HashMap<String, String> getInfo() throws IOException {
        HashMap<String, String> result = new HashMap<String, String>();

        String[] domains = {".co.uk", ".com", ".ca", ".de", ".fr",
                            ".it", ".nl", ".es", ".in", ".co.jp",
                            ".com.sg", ".cn", ".com.tr", ".com.mx",
                            ".com.au", ".com.br"};

        String curDomain = domains[0];
        String searchPage = "https://www.amazon" + curDomain + "/s/field-keywords=" + this.productName;
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Element elProduct = doc.select("#result_" + this.resultIndex).first();

        Element elName = elProduct.select("h2").first();
        String name = elName.text();
        result.put("name", name);

        Element elImg = elProduct.select("img").first();
        String image = elImg.absUrl("src");
        result.put("image", image);

        Element elURL = elProduct.select("a").first();
        String URL = elURL.attr("href");
        result.put("url", URL);

        Element elPrice = elProduct.select("span.a-size-base").first();
        String price = elPrice.text();
        result.put("price", price);

        Element elRating = elProduct.select("span.a-icon-alt").last();
        String rating = elRating.text().replace(" out of 5", "").replace(" stars", "");
        result.put("rating", rating);

        return result;
    }


}

