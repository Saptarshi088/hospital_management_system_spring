package com.saptarshi.HospitalManagement.repository;

import com.saptarshi.HospitalManagement.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}