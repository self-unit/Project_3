package com.codeclan.project.Project3.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public class Scraper {
    private String productName;
    private String ASIN;
    private int rating;

    public Scraper(String productName) {
        this.productName = productName.replace(" ", "%20");
        this.ASIN = null;
//        this.rating = null;
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

    public void getASIN() throws IOException {
        String searchPage = "https://www.amazon.co.uk/s/field-keywords=" + this.productName;
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

//        System.out.println(searchPage);
//        System.out.println(doc);

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

            Element product = doc.select("#result_" + iResult).first();
            Element elASIN = product.select("[data-asin]").first();

            this.ASIN = elASIN.attr("data-asin");
        }
    }

    public HashMap<String, String> getInfo(String domain) throws IOException {
        HashMap<String, String> result = new HashMap<String, String>();

        String searchPage = "https://www.amazon" + domain + "/s/field-keywords=" + this.productName; // ASIN
        String html = Jsoup.connect(searchPage).get().html();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        System.out.println(doc.html());
        System.out.println(searchPage);

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

//            Element elRating = elProduct.select("span.a-icon-alt").last(); // GRAB WITH ASIN IN ENGLISH
//            String rating = elRating.text().replace(" out of 5", "").replace(" stars", "");
//            result.put("rating", rating);
        }
        return result;
    }

    public void getAllCountriesPrices() throws IOException {

        // not working:, ".cn", ".nl", ".in"

        String[] domains = {".co.uk", ".com", ".de", ".fr",
                ".it", ".es", ".co.jp", ".com.mx", ".com.br", ".ca"};

        for (int i = 0; i < domains.length; i++) {
            System.out.println(domains[i]);
            System.out.println(getInfo(domains[i]));
        }
    }


}

