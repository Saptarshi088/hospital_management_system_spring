package com.saptarshi.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class AppointmentDto {
    Long id;
    LocalDate appointmentDate;
    String reason;
}