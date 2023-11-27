package com.texoit.exercises.awardsapi.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MovieController {

    @GetMapping("/awards/producers")
    public ResponseEntity getProducers() {
        return ResponseEntity.noContent().build();
    }
}
