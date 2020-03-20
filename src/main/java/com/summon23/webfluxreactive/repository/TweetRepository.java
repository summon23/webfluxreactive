package com.summon23.webfluxreactive.repository;

import com.summon23.webfluxreactive.entity.Tweet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends ReactiveMongoRepository<Tweet, String> {
}
