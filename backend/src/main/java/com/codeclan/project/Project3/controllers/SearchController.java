package com.codeclan.project.Project3.controllers;

import com.codeclan.project.Project3.models.Country;
import com.codeclan.project.Project3.models.Scraper;
import com.codeclan.project.Project3.models.Search;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(value = "/{item}", method = RequestMethod.GET)
    public Search getPricesForItem(@PathVariable String item) throws IOException {
        Scraper scraper = new Scraper(item);
        return scraper.getAllCountriesPrices();
    }
}