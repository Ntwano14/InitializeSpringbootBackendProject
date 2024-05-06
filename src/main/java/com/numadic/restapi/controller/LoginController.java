package com.numadic.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin
public class LoginController {

	Authentication authentication;

    @GetMapping("/login/get")
    public String loginForm() {
        return "login"; // Returns the login form
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Authenticate the user
        authentication = new UsernamePasswordAuthenticationToken(username, password);
        
        // If authentication is successful, redirect to some page
        return "login";
    }
}

