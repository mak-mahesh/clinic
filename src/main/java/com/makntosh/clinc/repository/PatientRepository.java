package com.makntosh.clinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.makntosh.clinc.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
