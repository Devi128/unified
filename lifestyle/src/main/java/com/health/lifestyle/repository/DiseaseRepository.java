package com.health.lifestyle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.health.lifestyle.model.Disease;

public interface DiseaseRepository extends MongoRepository<Disease, String> {

    Disease findByNameIgnoreCase(String name);
}