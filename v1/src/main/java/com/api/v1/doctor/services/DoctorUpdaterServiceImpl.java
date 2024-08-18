package com.api.v1.doctor.services;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.user.domain.User;
import com.api.v1.user.domain.UserRepository;
import com.api.v1.user.dtos.UpdateUserRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class DoctorUpdaterServiceImpl implements DoctorUpdaterService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    @Override
    public Mono<Doctor> update(@DLN String doctorLicenseNumber, @Valid UpdateUserRequestDto dto) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMap(doctor -> {
                    User updatedUser = doctor.getUser().update(dto);
                    return userRepository
                            .save(updatedUser)
                            .flatMap(user -> {
                                doctor.update(user);
                                return doctorRepository.save(doctor);
                            });
                });
    }

}
