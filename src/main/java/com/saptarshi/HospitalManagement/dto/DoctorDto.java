package com.saptarshi.HospitalManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.saptarshi.HospitalManagement.entities.Doctor}
 */
@Getter
@Setter
@AllArgsConstructor
public class DoctorDto {
    Long id;
    String name;
    String specilization;
    String email;
}