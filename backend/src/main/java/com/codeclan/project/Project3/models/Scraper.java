package com.codeclan.project.Project3.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Scraper {
    private String productName;
    private double rating;

    public Scraper(String productName) {
        this.productName = productName.replace(" ", "%20");
        this.rating = 0;
    }

    public boolean hasBadKeyword(String text) {
        String[] blacklist = {"did not match any products", "Nous avons trouvé", "Foram encontrados",
                                "Ihre Suche nach", "Votre recherche", "La ricerca", "La búsqueda",
                                "の検索に", "Nenhum produto"};
        for (int i = 0; i < blacklist.length; i++) {
            if(text.contains(blacklist[i])){
                return true;
            }
        }
        return false;
    }

    public String getProductASIN() throws IOException {
        String result = null;

        String searchPage = "https://www.amazon.co.uk/s/field-keywords=" + this.productName; // Add a check for null, try .com instead
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        if(!hasBadKeyword(doc.html())) {
            int iResult = 0;
            boolean found = false;

            while (found == false) {
                Element curProduct = doc.select("#result_" + iResult).first();
                if (!curProduct.html().contains("sponsored")) {
                    found = true;
                } else {
                    iResult++;
                }
            }

            Element elProduct = doc.select("#result_" + iResult).first();
            Element elRating = elProduct.select("span.a-icon-alt").first(); // GET RATING, NEED TO SEPERATE? - COULD DO WITH MORE SPECIFIC TAG
            this.rating = Double.parseDouble(elRating.text().replace(" out of 5", "").replace(" stars", ""));

            Element elASIN = elProduct.select("[data-asin]").first();
            result = elASIN.attr("data-asin");
        }

        return result;
    }

    public HashMap<String, String> getInfo(String domain, String ASIN) throws IOException {
        HashMap<String, String> result = new HashMap<String, String>();

        String searchPage = "https://www.amazon" + domain + "/s/field-keywords=" + ASIN; // ASIN
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

//        System.out.println(searchPage);

        if(!hasBadKeyword(doc.html())) {
            Element elProduct = doc.select("#result_0").first();

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
        } else {
            return null;
        }

        return result;
    }

    public void getAllCountriesPrices() throws IOException {
        String ASIN = getProductASIN();
        String[] domains = {".co.uk", ".com", ".de", ".fr", ".it"
                            , ".es", ".co.jp", ".com.mx", ".com.br"
                            , ".ca"}; // not working:, ".cn", ".nl", ".in"

        if(ASIN != null) {
            for (int i = 0; i < domains.length; i++) {
                HashMap<String, String> info = getInfo(domains[i], ASIN);
//                System.out.println(domains[i]);
                if(info != null) System.out.println(info);
            }
        } else {
            System.out.println("No results.");
        }

        HashMap<Product, ArrayList<CountryPrices>> finalResults;
//        Product product = new Product();
    }


}

