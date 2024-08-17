package com.api.v1.user.services;

import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.dtos.UpdateUserRequestDto;
import com.api.v1.user.dtos.UserResponseDto;
import com.api.v1.user.mappers.UserResponseMapper;
import com.api.v1.user.utils.UserFinderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserUpdatingServiceImpl implements UserUpdatingService {

    @Autowired
    private UserFinderUtil userFinder;

    @Autowired
    private UserRepository repository;

    @Override
    public Mono<UserResponseDto> update(String ssn, UpdateUserRequestDto data) {
        return userFinder
                .findBySsn(ssn)
                .flatMap(
                    b -> Mono.defer(() -> {
                        b.update(data);
                        Mono<User> updatedUser = repository.save(b);
                        return updatedUser.flatMap(e -> Mono.just(UserResponseMapper.map(e)));
        }));
    }

}
