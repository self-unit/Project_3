package com.codeclan.project.Project3.controllers;

import com.codeclan.project.Project3.models.Country;
import com.codeclan.project.Project3.models.Search;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(value = "/{item}", method = RequestMethod.GET)
    public Search getPricesForItem(@PathVariable String item) {
//        Scraper scraper = new Scraper(item);
        Country country1 = new Country("UK", 29.99, "http://www.google.com");
        Country country2 = new Country("US", 19.99, "http://www.yahoo.com");
        Search search = new Search(item, "http://www.jadow.com", 5.5);
        search.addCountry(country1);
        search.addCountry(country2);
        return search;
    }
}