package com.weavus.studyweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InitController {

@GetMapping("/")
    private String init() {

    return "index";
    }
}
