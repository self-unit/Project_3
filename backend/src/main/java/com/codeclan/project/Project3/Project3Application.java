package com.codeclan.project.Project3;

import com.codeclan.project.Project3.models.Scraper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Project3Application {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(Project3Application.class, args);
		Scraper scraper = new Scraper("Playstation 4 console");
		scraper.getAllCountriesPrices();
	}
}
