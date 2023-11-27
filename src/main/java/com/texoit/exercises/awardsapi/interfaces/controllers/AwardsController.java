package com.texoit.exercises.awardsapi.interfaces.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/awards")
class AwardsController {

    @GetMapping("/interval/producers")
    public ResponseEntity getProducers() {
        return ResponseEntity.noContent().build();
    }
}
