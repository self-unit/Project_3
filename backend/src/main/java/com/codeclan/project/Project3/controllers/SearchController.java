package com.codeclan.project.Project3.controllers;

import com.codeclan.project.Project3.models.Search;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/search")
    public HttpEntity<Search> search(
            @RequestParam(value = "name", required=false, defaultValue = "Word") String name)
    )
}
