package com.api.v1.patient.services;

import com.api.v1.patient.builders.PatientBuilder;
import com.api.v1.patient.domain.Patient;
import com.api.v1.patient.domain.PatientRepository;
import com.api.v1.patient.dtos.NewPatientRequestDto;
import com.api.v1.patient.dtos.PatientResponseDto;
import com.api.v1.patient.mapper.PatientResponseMapper;
import com.api.v1.patient.utils.PatientFinderUtil;
import com.api.v1.user.annotations.SSN;
import com.api.v1.user.builder.UserBuilder;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
class PatientUpdatingServiceImpl implements PatientUpdatingService {

    private final UserRepository userRepository;
    private final PatientFinderUtil patientFinderUtil;
    private final PatientRepository patientRepository;
    private final PatientBuilder patientBuilder;

    public PatientUpdatingServiceImpl(
        UserRepository userRepository, 
        PatientFinderUtil patientFinderUtil,
        PatientRepository patientRepository,
        PatientBuilder patientBuilder
    ) {
        this.userRepository = userRepository;
        this.patientFinderUtil = patientFinderUtil;
        this.patientRepository = patientRepository;
        this.patientBuilder = patientBuilder;
    }

    @Override
    public Mono<PatientResponseDto> update(@Valid NewPatientRequestDto dto) {
        return patientFinderUtil.find(dto.userDto().ssn())
                .flatMap(patient -> {
                    User oldUser = patient.getUser().archiveUser();
                    Mono<User> savedOldUser = userRepository.save(oldUser);
                    Patient oldPatient = patient.archivePatient();
                    Mono<Patient> savedOldPatient = patientRepository.save(oldPatient);
                    return Mono.defer(() -> {
                        User newUser = UserBuilder.createFromDto(dto.userDto()).build();
                        Mono<User> savedNewUser = userRepository.save(newUser);
                        return savedNewUser
                                .flatMap(user -> {
                                    Patient newPatient = patientBuilder
                                            .withUser(user)
                                            .withAddress(dto.address())
                                            .build();
                                    Mono<Patient> savedNewPatient = patientRepository.save(newPatient);
                                    return savedNewPatient.flatMap(p -> Mono.just(PatientResponseMapper.map(p)));
                                });
                    });
                });
    }

}
