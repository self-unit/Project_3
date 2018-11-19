package com.codeclan.project.Project3.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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

    public String getConversionRates() {
        // Number of Requests per Hour: 100 - Grab once every 1-24h?
        // http://free.currencyconverterapi.com/api/v5/convert?q=EUR_USD&compact=y
        // EUR_USD
        // EUR_GBP
        // EUR_MXN
        // GBP_USD
        // GBP_EUR
        // GBP_MXN
        // etc...
        return null;
    }

    // Country ref's: https://www.oracle.com/technetwork/java/javase/javase7locales-334809.html
    public float convertCurrency(String amount, Locale locale) throws ParseException {
        final NumberFormat format = NumberFormat.getNumberInstance(locale);
        if (format instanceof DecimalFormat) {
            ((DecimalFormat) format).setParseBigDecimal(true);
        }
        return (float) format.parse(amount.replaceAll("[^\\d.,]",""));
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
                if (!curProduct.html().contains("sponsored") && !curProduct.html().contains("Our Brand")) {
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

            try {
                Element elRating = elProduct.select("i.a-icon-star").first();
                this.rating = Double.parseDouble(elRating.text().replace(" out of 5", "").replace(" stars", ""));
            } catch (Exception e) {
                this.rating = 0;
            }

            Element elASIN = elProduct.select("[data-asin]").first();
            this.ASIN = elASIN.attr("data-asin");
        }
    }

    public Country getCountryInfo(String domain, String ASIN) {
        String searchPage = "https://www.amazon" + domain + "/gp/aws/cart/add.html?AssociateTag=your-tag-here&ASIN.1=" + ASIN + "&Quantity.1=1";
        String html = null;

        try { // Catches: "Remote host terminated the handshake" error
            html = Jsoup.connect(searchPage).get().html();
        } catch (IOException e) {
            System.out.println("DOMAIN: " + domain + ", ERROR CAUGHT: " + e.getMessage());
            return null;
        }

        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        String price = null;

        try {
            Elements elPrice = doc.select("td");
            Element e = elPrice.get(4);

            price = e.text();
        } catch (Exception e) {
            System.out.println("URL: " + searchPage + ", ERROR: " + e.getMessage());
            return null;
        }

        Country country = new Country(domain, price, searchPage);
        return country;
    }

    public Search getAllCountriesPrices() throws IOException {
        getProductConstants();
        Search search = new Search(this.productName, this.image, this.rating);
        String[] domains = {".co.uk", ".com", ".de", ".fr", ".it"
                , ".es", ".co.jp", ".com.mx", ".com.br"
                , ".ca"}; // not working:, ".cn", ".nl", ".in"

        if(ASIN != null) {
            for (int i = 0; i < domains.length; i++) {
                Country country = getCountryInfo(domains[i], ASIN);
                if(country != null){
                    search.addCountry(country);
                }
            }
        } else {
            System.out.println("No results.");
        }
        return search;
    }
}

