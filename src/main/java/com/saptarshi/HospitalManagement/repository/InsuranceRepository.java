package com.saptarshi.HospitalManagement.repository;

import com.saptarshi.HospitalManagement.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    Optional<Insurance> findByPolicyNumber(String policyNumber);

    boolean existsByPolicyNumber(String policyNumber);
}