package com.api.v1.doctor.services;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.domain.Doctor;
import com.api.v1.doctor.domain.DoctorRepository;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
class DoctorTerminatorServiceImpl implements DoctorTerminatorService {

    private final DoctorFinderUtil doctorFinderUtil;
    private final DoctorRepository doctorRepository;

    @Override
    public Mono<Doctor> terminate(@DLN String doctorLicenseNumber) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMap(doctor -> {
                    doctor.terminateDoctor();
                    return doctorRepository.save(doctor);
                });
    }

}
