package com.codeclan.project.Project3;

import com.codeclan.project.Project3.models.Scraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

@SpringBootApplication
public class Project3Application {

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);
//		Scraper scraper = new Scraper("PS4 Controller");
//		System.out.println(scraper.convertCurrency("R$ 1.999,99", Locale.forLanguageTag("pt"))); // Works for Brazil
//        scraper.getAllCountriesPrices();
	}
}
