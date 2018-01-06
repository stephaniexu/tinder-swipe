package com.playground.tinderswipe.repository;

import com.playground.tinderswipe.repository.entity.MatchEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<MatchEntity, String> {
}
