package com.saptarshi.HospitalManagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorRequest {
    @NotBlank
    @Size(max = 50)
    private String name;

    @Size(max = 100)
    private String specilization;

    @Email
    @NotBlank
    private String email;
}
