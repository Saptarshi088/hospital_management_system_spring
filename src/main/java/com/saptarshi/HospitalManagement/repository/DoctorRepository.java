package com.saptarshi.HospitalManagement.repository;

import com.saptarshi.HospitalManagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}