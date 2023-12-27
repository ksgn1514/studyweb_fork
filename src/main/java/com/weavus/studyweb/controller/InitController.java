package com.weavus.studyweb.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.weavus.studyweb.auth.PrincipalDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class InitController {

    @GetMapping("/")
    private String init(@AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest request) {
        if(principalDetails != null) {
            System.out.println(principalDetails.getUsername()) ; 
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", principalDetails.getUser());
            session.setMaxInactiveInterval(60 * 30);
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", null);
            session.setMaxInactiveInterval(60 * 30);
        }
        return "index";
    }
    @GetMapping("/home")
    private String home() {

        return "index";
    }

    @GetMapping("/community")
    private String community() {

        return "community";
    }

    @GetMapping("/loginForm")
    private String login() {

        return "login";
    }



}
