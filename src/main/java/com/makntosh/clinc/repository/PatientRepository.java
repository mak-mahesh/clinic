package com.makntosh.clinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.makntosh.clinc.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("SELECT COUNT(*) as PatientCount FROM Patient")
	int getTotalPatients();

	@Query("SELECT COUNT(*) from Patient where pdate=CURDATE()")
	int getTotalPatientsToday();

}
