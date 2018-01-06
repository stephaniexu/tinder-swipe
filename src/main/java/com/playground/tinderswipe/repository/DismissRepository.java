package com.playground.tinderswipe.repository;

import com.playground.tinderswipe.repository.entity.DismissEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DismissRepository extends MongoRepository<DismissEntity, ObjectId> {
    List<DismissEntity> findBySwiper(String swiperId);
}
