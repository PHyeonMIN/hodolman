package com.hodolog.controller;

import com.hodolog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController {

    // SSR -> jsp, thymeleaf, mustache, freemarker
        // -> html rendering
    // SPA -> vue(vue + SSR = nuxt), react(react + SSR = next)
        // -> javascript + <-> API (JSON)

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 글 등록
    // POST Method

    @PostMapping("/posts")
//    public String post(@RequestParam String title, @RequestParam String content) {
//    public String post(@RequestParam Map<String, String> params) {
    public String post(@RequestBody PostCreate params) {
//        log.info("title={}, content={}", title, content);
        log.info("params={}",params.toString());
        return "Hello World";
    }
}
