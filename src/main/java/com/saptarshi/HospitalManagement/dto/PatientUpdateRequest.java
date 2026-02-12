package com.saptarshi.HospitalManagement.dto;

import com.saptarshi.HospitalManagement.type.BloodGroupType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PatientUpdateRequest {

    @NotBlank
    private String name;

    @NotNull
    @Past
    private LocalDate birthDate;

    @Email
    @NotBlank
    private String email;

    private String gender;

    private BloodGroupType bloodGroup;

    @Valid
    @NotNull
    private AdmitPatientInsuranceDto insurance;
}
