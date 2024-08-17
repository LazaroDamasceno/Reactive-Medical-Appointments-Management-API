package com.api.v1.patient.services;

import com.api.v1.patient.builders.PatientBuilder;
import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.NewPatientRequestDto;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.mapper.PatientResponseMapper;
import com.api.v1.user.builder.UserBuilder;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.exceptions.DuplicatedSsnException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PatientRegistrationServiceImpl implements PatientRegistrationService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientBuilder patientBuilder;

    @Override
    public Mono<PatientResponseDto> register(@Valid NewPatientRequestDto dto) {
        return userRepository
                .findBySsn(dto.userDto().ssn())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) return handleDuplicatedSsn();
                    else return handleRegistration(dto);
                });
    }

    private Mono<PatientResponseDto> handleDuplicatedSsn() {
        return Mono.error(DuplicatedSsnException::new);
    }

    private Mono<PatientResponseDto> handleRegistration(NewPatientRequestDto dto) {
        return Mono.defer(() -> {
            User newUser = UserBuilder.createFromDto(dto.userDto()).build();
            Mono<User> savedUser = userRepository.save(newUser);
            Mono<String> monoAddress = Mono.just(dto.address());
            return Mono.zip(savedUser, monoAddress)
                    .flatMap(tuple -> {
                        User user = tuple.getT1();
                        String address = tuple.getT2();
                        Patient newPatient = patientBuilder
                                .withUser(user)
                                .withAddress(address)
                                .build();
                        return patientRepository.save(newPatient);
                    })
                    .flatMap(patient -> Mono.just(PatientResponseMapper.map(patient)));
        });
    }

}
