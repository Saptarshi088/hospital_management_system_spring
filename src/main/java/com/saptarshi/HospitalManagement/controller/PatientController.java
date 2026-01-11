package com.saptarshi.HospitalManagement.controller;

import com.saptarshi.HospitalManagement.dto.PatientDto;
import com.saptarshi.HospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public List<PatientDto> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id){
        return patientService.findPatientById(id);
    }

}
