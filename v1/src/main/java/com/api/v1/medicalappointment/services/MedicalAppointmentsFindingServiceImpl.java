package com.api.v1.medicalappointment.services;

import com.api.v1.doctor.annotations.DLN;
import com.api.v1.doctor.utils.DoctorFinderUtil;
import com.api.v1.medicalappointment.domain.MedicalAppointmentRepository;
import com.api.v1.medicalappointment.dtos.BetweenDatesRequestDto;
import com.api.v1.medicalappointment.dtos.MedicalAppointmentResponseDto;
import com.api.v1.medicalappointment.mapper.MedicalAppointmentResponseMapper;
import com.api.v1.patient.utils.PatientFinderUtil;
import com.api.v1.user.annotations.SSN;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
class MedicalAppointmentsFindingServiceImpl implements MedicalAppointmentsFindingService {

    private final MedicalAppointmentRepository repository;
    private final PatientFinderUtil patientFinderUtil;
    private final DoctorFinderUtil doctorFinderUtil;

    public MedicalAppointmentsFindingServiceImpl(
        MedicalAppointmentRepository repository,
        PatientFinderUtil patientFinderUtil,
        DoctorFinderUtil doctorFinderUtil
     ) {
        this.repository = repository;
        this.patientFinderUtil = patientFinderUtil;
        this.doctorFinderUtil = doctorFinderUtil;
}

@Override
    public Flux<MedicalAppointmentResponseDto> findAll() {
        return repository
                .findAll()
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)))
                .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActive() {
        return repository
                .findAll()
                .filter(e -> e.getFinishingDate() == null
                        && e.getCancellationDate() == null
                )
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)))
                .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findFinished() {
        return repository
                .findAll()
                .filter(e -> e.getFinishingDate() != null
                        && e.getCancellationDate() == null
                )
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)))
                .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCancelled() {
        return repository
                .findAll()
                .filter(e -> e.getFinishingDate() == null
                        && e.getCancellationDate() != null
                )
                .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e)))
                .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAllByPatient(@SSN String ssn) {
        return patientFinderUtil
                .find(ssn)
                .flatMapMany(patient -> repository
                        .findAll()
                        .filter(e -> e.getPatient().equals(patient))
                        .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActiveByPatient(@SSN String ssn) {
        return patientFinderUtil
                .find(ssn)
                .flatMapMany(patient -> repository
                        .findAll()
                        .filter(e -> e.getPatient().equals(patient)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() == null
                        )
                        .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findFinishedByPatient(@SSN String ssn) {
        return patientFinderUtil
                .find(ssn)
                .flatMapMany(patient -> repository
                        .findAll()
                        .filter(e -> e.getPatient().equals(patient)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() != null
                        )
                        .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCancelledByPatient(@SSN String ssn) {
        return patientFinderUtil
                .find(ssn)
                .flatMapMany(patient -> repository
                        .findAll()
                        .filter(e -> e.getPatient().equals(patient)
                                && e.getCancellationDate() != null
                                && e.getFinishingDate() == null
                        )
                        .flatMap(e -> Flux.just(MedicalAppointmentResponseMapper.map(e))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAllByPatient(@SSN String ssn, @Valid BetweenDatesRequestDto dates) {
        return patientFinderUtil
                .find(ssn)
                .flatMapMany(patient -> repository
                        .findAll()
                        .filter(e -> e.getPatient().equals(patient)
                                && (
                                        converter(e.getBookingDate()).isEqual(dates.firstDate())
                                        || converter(e.getBookingDate()).isAfter(dates.firstDate())
                                ) && (
                                        converter(e.getBookingDate()).isEqual(dates.lastDate())
                                        || converter(e.getBookingDate()).isBefore(dates.lastDate())
                                ))
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActiveByPatient(
            @SSN String ssn, 
            @Valid BetweenDatesRequestDto dates
    ) {
        return patientFinderUtil
                .find(ssn)
                .flatMapMany(patient -> repository
                        .findAll()
                        .filter(e -> e.getPatient().equals(patient)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() == null
                                && (
                                        converter(e.getBookingDate()).isEqual(dates.firstDate())
                                        || converter(e.getBookingDate()).isAfter(dates.firstDate())
                                    )
                                    && (
                                        converter(e.getBookingDate()).isEqual(dates.lastDate())
                                        || converter(e.getBookingDate()).isBefore(dates.lastDate())
                        ))
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findFinishedByPatient(
            @SSN String ssn, 
            @Valid BetweenDatesRequestDto dates
    ) {
        return patientFinderUtil
                .find(ssn)
                .flatMapMany(patient -> repository
                        .findAll()
                        .filter(e -> e.getPatient().equals(patient)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() != null
                                && (
                                        converter(e.getBookingDate()).isEqual(dates.firstDate())
                                        || converter(e.getBookingDate()).isAfter(dates.firstDate())
                                    )
                                    && (
                                        converter(e.getBookingDate()).isEqual(dates.lastDate())
                                        || converter(e.getBookingDate()).isBefore(dates.lastDate())
                        ))
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAllByDoctor(@DLN String doctorLicenseNumber) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMapMany(doctor -> repository
                            .findAll()
                            .filter(e -> e.getDoctor().equals(doctor))
                            .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                            .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActiveByDoctor(@DLN String doctorLicenseNumber) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMapMany(doctor -> repository
                        .findAll()
                        .filter(e -> e.getDoctor().equals(doctor)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() == null
                        )
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findFinishedByDoctor(@DLN String doctorLicenseNumber) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMapMany(doctor -> repository
                        .findAll()
                        .filter(e -> e.getDoctor().equals(doctor)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() != null
                        )
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findCancelledByDoctor(@DLN String doctorLicenseNumber) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMapMany(doctor -> repository
                        .findAll()
                        .filter(e -> e.getDoctor().equals(doctor)
                                && e.getCancellationDate() != null
                                && e.getFinishingDate() == null
                        )
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findAllByDoctor(
            @DLN String doctorLicenseNumber,
            @Valid BetweenDatesRequestDto dates
    ) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMapMany(doctor -> repository
                        .findAll()
                        .filter(e -> e.getDoctor().equals(doctor)
                                && (
                                            converter(e.getBookingDate()).isEqual(dates.firstDate())
                                            || converter(e.getBookingDate()).isAfter(dates.firstDate())
                                        ) && (
                                            converter(e.getBookingDate()).isEqual(dates.lastDate())
                                            || converter(e.getBookingDate()).isBefore(dates.lastDate())
                                ))
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findActiveByDoctor(
            @DLN String doctorLicenseNumber,
            @Valid BetweenDatesRequestDto dates
    ) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMapMany(doctor -> repository
                        .findAll()
                        .filter(e -> e.getDoctor().equals(doctor)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() == null
                                && (
                                        converter(e.getBookingDate()).isEqual(dates.firstDate())
                                        || converter(e.getBookingDate()).isAfter(dates.firstDate())
                                    )
                                    && (
                                        converter(e.getBookingDate()).isEqual(dates.lastDate())
                                        || converter(e.getBookingDate()).isBefore(dates.lastDate())
                        ))
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }

    @Override
    public Flux<MedicalAppointmentResponseDto> findFinishedByDoctor(
            @DLN String doctorLicenseNumber,
            @Valid BetweenDatesRequestDto dates
    ) {
        return doctorFinderUtil
                .find(doctorLicenseNumber)
                .flatMapMany(doctor -> repository
                        .findAll()
                        .filter(e -> e.getDoctor().equals(doctor)
                                && e.getCancellationDate() == null
                                && e.getFinishingDate() != null
                                && (
                                        converter(e.getBookingDate()).isEqual(dates.firstDate())
                                        || converter(e.getBookingDate()).isAfter(dates.firstDate())
                                    ) 
                                    && (
                                        converter(e.getBookingDate()).isEqual(dates.lastDate())
                                        || converter(e.getBookingDate()).isBefore(dates.lastDate())
                        ))
                        .flatMap(b -> Flux.just(MedicalAppointmentResponseMapper.map(b))))
                        .cache();
    }
    
    private LocalDateTime converter(String date) {
        return LocalDateTime.parse(date);
    }

}
