package com.saptarshi.HospitalManagement.controller;

import com.saptarshi.HospitalManagement.dto.DoctorDto;
import com.saptarshi.HospitalManagement.dto.DoctorRequest;
import com.saptarshi.HospitalManagement.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorDto> list() {
        return doctorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDto> get(@PathVariable Long id) {
        return doctorService.getById(id);
    }

    @PostMapping
    public DoctorDto create(@Valid @RequestBody DoctorRequest request) {
        return doctorService.create(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorDto> update(@PathVariable Long id, @Valid @RequestBody DoctorRequest request) {
        return doctorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return doctorService.delete(id);
    }
}
