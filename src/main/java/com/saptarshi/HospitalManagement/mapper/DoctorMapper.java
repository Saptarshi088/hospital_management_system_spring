package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.DoctorDto;
import com.saptarshi.HospitalManagement.entities.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDto toDto(Doctor doctor);
}
