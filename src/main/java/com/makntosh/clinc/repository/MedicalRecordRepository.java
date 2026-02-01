package com.makntosh.clinc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.makntosh.clinc.model.MedicalRecord;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

	/**
	 * Find all medical records for a specific patient
	 * @param patientId - Patient ID
	 * @return List of medical records
	 */
	@Query("SELECT mr FROM MedicalRecord mr WHERE mr.patient.pid = :patientId ORDER BY mr.visitDate DESC")
	List<MedicalRecord> findByPatientId(@Param("patientId") long patientId);

	/**
	 * Find recent medical records for a patient (last 5 visits)
	 * @param patientId - Patient ID
	 * @return List of recent medical records
	 */
	@Query("SELECT mr FROM MedicalRecord mr WHERE mr.patient.pid = :patientId ORDER BY mr.visitDate DESC LIMIT 5")
	List<MedicalRecord> findRecentRecordsByPatientId(@Param("patientId") long patientId);

	/**
	 * Count total medical records for a patient
	 * @param patientId - Patient ID
	 * @return Total count of medical records
	 */
	@Query("SELECT COUNT(mr) FROM MedicalRecord mr WHERE mr.patient.pid = :patientId")
	int countRecordsByPatientId(@Param("patientId") long patientId);

	/**
	 * Find medical records by diagnosis
	 * @param diagnosis - Diagnosis keyword
	 * @return List of medical records matching diagnosis
	 */
	@Query("SELECT mr FROM MedicalRecord mr WHERE mr.diagnosis LIKE %:diagnosis% ORDER BY mr.visitDate DESC")
	List<MedicalRecord> findByDiagnosis(@Param("diagnosis") String diagnosis);
}