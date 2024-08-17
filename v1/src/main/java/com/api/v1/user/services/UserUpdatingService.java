package com.api.v1.user.services;

import com.api.v1.user.dtos.UpdateUserRequestDto;
import com.api.v1.user.dtos.UserResponseDto;
import reactor.core.publisher.Mono;

public interface UserUpdatingService {

    Mono<UserResponseDto> update(String ssn, UpdateUserRequestDto data);

}
