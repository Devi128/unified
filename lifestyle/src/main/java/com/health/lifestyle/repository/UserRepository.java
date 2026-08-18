package com.health.lifestyle.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.health.lifestyle.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}