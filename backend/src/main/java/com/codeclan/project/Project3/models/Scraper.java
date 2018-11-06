package com.codeclan.project.Project3.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Scraper {
    private String searchName;
    private String productName;
    private String image;
    private String ASIN;
    private double rating;

    public Scraper(String searchName) {
        this.searchName = searchName.replace(" ", "%20");
        this.productName = null;
        this.image = null;
        this.ASIN = null;
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

    public void getProductConstants() throws IOException {
        String searchPage = "https://www.amazon.co.uk/s/field-keywords=" + this.searchName; // Add a check for null, try .com instead
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

            Element elName = elProduct.select("h2").first();
            this.productName = elName.text();

            Element elImg = elProduct.select("img").first();
            this.image = elImg.absUrl("src");

            Element elRating = elProduct.select("span.a-icon-alt").first(); // GET RATING, NEED TO SEPARATE? - COULD DO WITH MORE SPECIFIC TAG
            this.rating = Double.parseDouble(elRating.text().replace(" out of 5", "").replace(" stars", ""));

            Element elASIN = elProduct.select("[data-asin]").first();
            this.ASIN = elASIN.attr("data-asin");
        }
    }

    public Country getCountryInfo(String domain, String ASIN) throws IOException {
        String searchPage = "https://www.amazon" + domain + "/s/field-keywords=" + ASIN; // ASIN
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        if(!hasBadKeyword(doc.html())) {
            Element elProduct = doc.select("#result_0").first();

//            Element elName = elProduct.select("h2").first();
//            String name = elName.text();
//
//            Element elImg = elProduct.select("img").first();
//            String image = elImg.absUrl("src");

            Element elURL = elProduct.select("a").first();
            String url = elURL.attr("href");

            Element elPrice = elProduct.select("span.a-size-base").first();
            String price = elPrice.text();

            Country country = new Country(domain, price, url);
            return country;
        }

        return null;
    }

    public void getAllCountriesPrices() throws IOException {
        Search search = new Search();

        getProductConstants();
        String[] domains = {".co.uk", ".com", ".de", ".fr", ".it"
                            , ".es", ".co.jp", ".com.mx", ".com.br"
                            , ".ca"}; // not working:, ".cn", ".nl", ".in"

        if(ASIN != null) {
            for (int i = 0; i < domains.length; i++) {
                Country country = getCountryInfo(domains[i], ASIN);
                if(country != null){
                    System.out.println(country);
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(product);
                    System.out.println(json);
                }
            }
        } else {
            System.out.println("No results.");
        }
    }
}

