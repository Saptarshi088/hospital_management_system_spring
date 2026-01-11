package com.saptarshi.HospitalManagement.mapper;

import com.saptarshi.HospitalManagement.dto.AppointmentDto;
import com.saptarshi.HospitalManagement.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@SuppressWarnings("unused")
@Mapper(componentModel = "spring")
public interface AppointMapper {
    AppointmentDto toDto(Appointment appointment);
}
