package com.api.v1.user.domain;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    @Query(" 'ssn': {$eq ?0}, archivingDate: null ")
    Mono<User> findBySsn(String ssn);

}
