package com.saptarshi.HospitalManagement.repository;

import com.saptarshi.HospitalManagement.dto.AppointmentDto;
import com.saptarshi.HospitalManagement.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
}
