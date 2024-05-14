package com.team1.technikon.securityservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admintest")
    public String adminTest() {
        return "Welcome";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/usertest")
    public String userTest() {
        return "Welcome";
    }
}
