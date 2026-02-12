package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.DoctorDto;
import com.saptarshi.HospitalManagement.dto.DoctorRequest;
import com.saptarshi.HospitalManagement.entities.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    DoctorDto toDto(Doctor doctor);

    Doctor toEntity(DoctorRequest request);

    void updateEntityFromRequest(DoctorRequest request, @MappingTarget Doctor doctor);
}
