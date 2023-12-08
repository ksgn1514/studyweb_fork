package com.weavus.studyweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

    @GetMapping("/")
    private String init() {

        return "index";
    }
    @GetMapping("/home")
    private String home() {

        return "index";
    }

    @GetMapping("/login")
    private String login() {

        return "login";
    }

    @GetMapping("/join")
    private String join() {

        return "join";
    }

    //
    @GetMapping("/studys")
    private String studys() {

        return "studys";
    }
    @GetMapping("/community")
    private String community() {

        return "community";
    }

    @GetMapping("/studyenroll")
    private String studyenroll() {

        return "studyenroll";
    }

    @GetMapping("/studydetail")
    private String studydetail() {

        return "studydetail";
    }



}
