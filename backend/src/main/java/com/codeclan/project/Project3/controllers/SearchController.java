package com.codeclan.project.Project3.controllers;

import com.codeclan.project.Project3.resources.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/search")
public class SearchController {

    @RequestMapping(value = "/{item}", method = RequestMethod.GET)
    public Search getPricesForItem(@PathVariable String item) {
        Scraper scraper = new Scraper(item)
        return scraper.getAllCountriesPrices();
    }
}