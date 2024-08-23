package com.api.v1.user.interfaces;

import com.api.v1.user.dtos.NewUserRequestDto;
import com.api.v1.user.dtos.UserResponseDto;
import reactor.core.publisher.Mono;

public interface UserRegistrationService {

    Mono<UserResponseDto> register(NewUserRequestDto dto);

}
