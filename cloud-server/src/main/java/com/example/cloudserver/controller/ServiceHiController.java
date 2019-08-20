package com.example.cloudserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class ServiceHiController {

    @Value("hello world")
    String port;

    @GetMapping("/hi")
    public String home(String name) {
        return "hi " + name + ",i am from port:" + port;
    }
}