package com.api.v1.user.services;

import com.api.v1.user.builder.UserBuilder;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.dtos.NewUserRequestDto;
import com.api.v1.user.dtos.UserResponseDto;
import com.api.v1.user.exceptions.DuplicatedSsnException;
import com.api.v1.user.mappers.UserResponseMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class UserRegistrationServiceImpl implements UserRegistrationService {

    @Autowired
    private UserRepository repository;

    @Override
    public Mono<UserResponseDto> register(@Valid NewUserRequestDto dto) {
        return repository
                .findBySsn(dto.ssn())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(DuplicatedSsnException::new);
                    } else {
                        return Mono.defer(() -> {
                            User user = UserBuilder.createFromDto(dto).build();
                            Mono<User> savedUser = repository.save(user);
                            return savedUser.flatMap(b -> Mono.just(UserResponseMapper.map(b)));
                        });
                    }
                });
    }

}
