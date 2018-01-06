package com.playground.tinderswipe.repository;

import com.playground.tinderswipe.repository.entity.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountEntity, String> {
}
