package com.example.stockmarketspringapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    @GetMapping("/test")
    public String testOAuth() {
        return "OAuth endpoint is working!";
    }
}
