package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HiddenFeatureController {

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://numbersapi.com/" + number;
        String fact = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok(fact);
    }
}