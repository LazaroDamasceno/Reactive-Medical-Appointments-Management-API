package com.api.v1.medicalappointment.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BetweenDatesRequestDto(
        @NotNull LocalDateTime firstDate,
        @NotNull LocalDateTime lastDate
) {
}
