package com.hodolog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    // SSR -> jsp, thymeleaf, mustache, freemarker
        // -> html rendering
    // SPA -> vue(vue + SSR = nuxt), react(react + SSR = next)
        // -> javascript + <-> API (JSON)

    @GetMapping("/posts")
    public String get() {
        return "Hello World";
    }
}
