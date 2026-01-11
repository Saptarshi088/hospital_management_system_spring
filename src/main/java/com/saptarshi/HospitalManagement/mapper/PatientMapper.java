package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.PatientDto;
import com.saptarshi.HospitalManagement.entities.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(source="insurance.id", target = "insurance_id")
    PatientDto toDto(Patient patient);
}
