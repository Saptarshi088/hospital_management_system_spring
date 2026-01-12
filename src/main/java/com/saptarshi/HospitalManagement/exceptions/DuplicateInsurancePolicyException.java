package com.saptarshi.HospitalManagement.exceptions;

public class DuplicateInsurancePolicyException extends RuntimeException {
    public DuplicateInsurancePolicyException(String message) {
        super(message);
    }
}