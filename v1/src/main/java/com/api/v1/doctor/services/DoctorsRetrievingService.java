package com.api.v1.doctor.services;

import com.api.v1.doctor.domain.Doctor;
import reactor.core.publisher.Flux;

public interface DoctorsRetrievingService {

    Flux<Doctor> findAll();

}
