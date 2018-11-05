package com.codeclan.project.Project3.controllers;

import com.codeclan.project.Project3.models.Search;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class SearchController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/search")
    public HttpEntity<Search> search(
            @RequestParam(value = "name", required=false, defaultValue = "Word") String name) {

        Search search = new Search(String.format(TEMPLATE, name));
        search.add(linkTo(methodOn(SearchController.class).search(name)).withSelfRel());

        return new ResponseEntity<>(search, HttpStatus.OK);
    }
}