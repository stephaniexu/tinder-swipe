package com.playground.tinderswipe.repository;

import com.playground.tinderswipe.repository.entity.FavoriteEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FavoriteRepository extends MongoRepository<FavoriteEntity, ObjectId> {
    List<FavoriteEntity> findBySwiper(String swiperId);
}
