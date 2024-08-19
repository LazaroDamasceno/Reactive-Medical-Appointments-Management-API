package com.api.v1.medicalappointment.services;

import com.api.v1.medicalappointment.domain.MedicalAppointmentRepository;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.medicalappointment.mapper.MedicalAppointmentResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
class MedicalAppointmentsFindingServiceImpl implements MedicalAppointmentsFindingService {

    private final MedicalAppointmentRepository repository;

    @Override
    public Flux<MedicalAppointmentResponseDto> findAll() {
        return repository
                .findAll()
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActive() {
        return repository
                .findAll()
                .filter(e -> e.getFinishingDate() == null
                        && e.getCancellationDate() == null
                )
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findFinished() {
        return repository
                .findAll()
                .filter(e -> e.getFinishingDate() != null
                        && e.getCancellationDate() == null
                )
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)));
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCancelled() {
        return repository
                .findAll()
                .filter(e -> e.getFinishingDate() == null
                        && e.getCancellationDate() != null
                )
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)));
    }



}
