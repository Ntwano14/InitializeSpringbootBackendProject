package com.numadic.restapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class LoginController {

    @GetMapping("/login")
    public String login(Authentication authentication) {

        String userName = authentication.getName();

        return "Spring Security In-memory Authentication Example - Welcome " + userName;
    }
}
