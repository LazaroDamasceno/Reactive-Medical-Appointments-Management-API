package com.api.v1.user.services;

import org.springframework.stereotype.Service;
import com.api.v1.user.builder.UserBuilder;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.dtos.NewUserRequestDto;
import com.api.v1.user.dtos.UserResponseDto;
import com.api.v1.user.exceptions.DuplicatedSsnException;
import com.api.v1.user.interfaces.UserRegistrationService;
import com.api.v1.user.mappers.UserResponseMapper;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@Service
class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserRepository repository;

    public UserRegistrationServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserResponseDto> register(@Valid NewUserRequestDto dto) {
        return repository
                .findAll()
                .filter(e -> e.getSsn().equals(dto.ssn())
                        && e.getArchivingDate() == null
                )
                .hasElements()
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
