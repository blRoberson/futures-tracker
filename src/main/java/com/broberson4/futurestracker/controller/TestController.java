package com.broberson4.futurestracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
        @GetMapping("/")
        public String home() {
            return "Futures Tracker API is running.";
        }
}
