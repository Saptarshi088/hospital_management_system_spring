package com.saptarshi.HospitalManagement.dto;

import com.saptarshi.HospitalManagement.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PatientInAppointmentResponseDto {

    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    private LocalDateTime createdAt;

    private BloodGroupType bloodGroup;


}
