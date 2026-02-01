package com.makntosh.clinc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.makntosh.clinc.model.PatientAllergy;

@Repository
public interface PatientAllergyRepository extends JpaRepository<PatientAllergy, Long> {

	/**
	 * Find all active allergies for a specific patient
	 * @param patientId - Patient ID
	 * @return List of active allergies
	 */
	@Query("SELECT pa FROM PatientAllergy pa WHERE pa.patient.pid = :patientId AND pa.isActive = true")
	List<PatientAllergy> findActiveAllergiesByPatientId(@Param("patientId") long patientId);

	/**
	 * Find all allergies (active and inactive) for a specific patient
	 * @param patientId - Patient ID
	 * @return List of all allergies
	 */
	@Query("SELECT pa FROM PatientAllergy pa WHERE pa.patient.pid = :patientId")
	List<PatientAllergy> findAllAllergiesByPatientId(@Param("patientId") long patientId);

	/**
	 * Find allergies by severity level
	 * @param patientId - Patient ID
	 * @param severity - Severity level (Mild, Moderate, Severe)
	 * @return List of allergies with specified severity
	 */
	@Query("SELECT pa FROM PatientAllergy pa WHERE pa.patient.pid = :patientId AND pa.severity = :severity AND pa.isActive = true")
	List<PatientAllergy> findAllergiesBySeverity(@Param("patientId") long patientId, @Param("severity") String severity);
}