package com.saptarshi.HospitalManagement.repository;

import com.saptarshi.HospitalManagement.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}