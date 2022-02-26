package com.move.telegrambot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HerokuTest {
    

    @GetMapping("/")
    String hello() {
        return "This tutorial is the best. All hail the great Kristijan.";
    }

}
