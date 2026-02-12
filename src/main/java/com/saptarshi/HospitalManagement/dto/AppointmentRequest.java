package com.saptarshi.HospitalManagement.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate appointmentDate;

    @NotBlank
    private String reason;

    @NotNull
    private Long patientId;

    @NotNull
    private Long doctorId;
}
