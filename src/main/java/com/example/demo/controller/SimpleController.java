package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simple")
public class SimpleController {

    @GetMapping("/holaMessage")
    public String holaMethod() {
        return "Hola";
    }
}
