package com.api.v1.user.utils;

import com.api.v1.user.annotations.SSN;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserFinderUtil {

    private final UserRepository repository;

        public UserFinderUtil(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<User> find(@SSN String ssn) {
        return repository
                .findBySsn(ssn)
                .switchIfEmpty(Mono.error(UserNotFoundException::new));
    }

}
