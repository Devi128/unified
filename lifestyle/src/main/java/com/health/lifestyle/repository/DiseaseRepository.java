package com.health.lifestyle.repository;

import com.health.lifestyle.model.Disease;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends MongoRepository<Disease, String> {

    Disease findByName(String name);
}