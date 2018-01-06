package com.playground.tinderswipe.repository;

import com.playground.tinderswipe.repository.entity.GreetingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GreetingRepository extends MongoRepository<GreetingEntity, String> {
    List<GreetingEntity> findByUser(String user);
}
