package com.health.lifestyle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.lifestyle.model.Disease;
import com.health.lifestyle.repository.DiseaseRepository;

@RestController
@RequestMapping("/diseases")
public class DiseaseController {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @PostMapping
    public Disease addDisease(@RequestBody Disease disease) {
        return diseaseRepository.save(disease);
    }

    @GetMapping
    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }
    @PostMapping("/bulk")
public List<Disease> addAllDiseases(@RequestBody List<Disease> diseases) {
    return diseaseRepository.saveAll(diseases);
}
}