package com.summon23.webfluxreactive.controller;

import com.summon23.webfluxreactive.entity.Tweet;
import com.summon23.webfluxreactive.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MainController {

    @Autowired
    private TweetRepository tweetRepository;

    @GetMapping("/tweets")
    public Flux<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @GetMapping("/tweets/{id}")
    public Mono<ResponseEntity<Tweet>> getTweetsById(
            @PathVariable(value = "id") String tweetsId) {
        return tweetRepository.findById(tweetsId)
                .map(savedTweet -> ResponseEntity.ok(savedTweet))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tweet> getAllTweetsStream() {
        return tweetRepository.findAll();
    }

}
