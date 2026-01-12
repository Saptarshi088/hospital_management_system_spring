package com.saptarshi.HospitalManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateInsurancePolicyException.class)
    public ResponseEntity<?> handleDuplicatePolicy(
            DuplicateInsurancePolicyException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of(
                        "error", "DUPLICATE_POLICY_NUMBER",
                        "message", ex.getMessage()
                ));
    }
}
