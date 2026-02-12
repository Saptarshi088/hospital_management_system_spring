-- Flyway baseline schema for Hospital Management
CREATE TABLE insurance (
    id BIGSERIAL PRIMARY KEY,
    policy_number VARCHAR(50) NOT NULL UNIQUE,
    provider VARCHAR(100) NOT NULL,
    valid_until DATE NOT NULL,
    created_at DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    birth_date DATE,
    email VARCHAR(120) NOT NULL UNIQUE,
    gender VARCHAR(20),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    blood_group VARCHAR(20),
    insurance_id BIGINT REFERENCES insurance(id)
);

CREATE INDEX idx_patient_birth_date ON patient(birth_date);

CREATE TABLE doctor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    specilization VARCHAR(100),
    email VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE department (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    head_doctor_id BIGINT REFERENCES doctor(id)
);

CREATE TABLE my_dpt_doctors (
    dpt_id BIGINT NOT NULL REFERENCES department(id),
    doctor_id BIGINT NOT NULL REFERENCES doctor(id),
    PRIMARY KEY (dpt_id, doctor_id)
);

CREATE TABLE appointment (
    id BIGSERIAL PRIMARY KEY,
    appointment_date DATE NOT NULL,
    reason VARCHAR(500) NOT NULL,
    patient_id BIGINT NOT NULL REFERENCES patient(id),
    doctor_id BIGINT NOT NULL REFERENCES doctor(id)
);

CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    doctor_id BIGINT,
    patient_id BIGINT
);
