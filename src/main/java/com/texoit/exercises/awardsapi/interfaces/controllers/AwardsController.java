package com.texoit.exercises.awardsapi.interfaces.controllers;

import com.texoit.exercises.awardsapi.application.services.MovieService;
import com.texoit.exercises.awardsapi.application.usecases.AwardsInterval;
import com.texoit.exercises.awardsapi.domain.valueobjects.ProducersAwards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/awards")
class AwardsController {

    @Autowired
    private MovieService service;

    @GetMapping("/interval/producers")
    public ResponseEntity<ProducersAwards> getByProducers() {
        AwardsInterval useCase = new AwardsInterval(service);
        return ResponseEntity.ok(useCase.findByProducers());
    }

    @GetMapping("/interval/studios")
    public ResponseEntity<ProducersAwards> getByStudios() {
        AwardsInterval useCase = new AwardsInterval(service);
        return ResponseEntity.ok(useCase.findByProducers());
    }

}
