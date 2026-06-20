package com.makntosh.clinc.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.makntosh.clinc.model.MedicalRecord;
import com.makntosh.clinc.repository.MedicalRecordRepository;

/**
 * REST Controller for managing medical records, a controller class
 * @author Admin
 * @version 1.0
 * @since 2021
 */
@RestController
@RequestMapping("/api/medical-records")
@CrossOrigin(origins = "http://localhost:3000")
public class MedicalRecordController {

	@Autowired
	MedicalRecordRepository medicalRecordRepo;

	/**
	 * Get all medical records
	 * @return List of all medical records
	 */
	@GetMapping("/all")
	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecordRepo.findAll();
	}

	/**
	 * Get medical record by ID
	 * @param recordId - Medical record ID
	 * @return Medical record or 404 if not found
	 */
	@GetMapping("/{recordId}")
	public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable long recordId) {
		Optional<MedicalRecord> record = medicalRecordRepo.findById(recordId);
		if (record.isPresent()) {
			return ResponseEntity.ok(record.get());
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Get all medical records for a specific patient
	 * @param patientId - Patient ID
	 * @return List of medical records for the patient
	 */
	@GetMapping("/patient/{patientId}")
	public List<MedicalRecord> getMedicalRecordsByPatientId(@PathVariable long patientId) {
		return medicalRecordRepo.findByPatientId(patientId);
	}

	/**
	 * Get recent medical records for a patient (last 5 visits)
	 * @param patientId - Patient ID
	 * @return List of recent medical records
	 */
	@GetMapping("/patient/{patientId}/recent")
	public List<MedicalRecord> getRecentMedicalRecords(@PathVariable long patientId) {
		return medicalRecordRepo.findRecentRecordsByPatientId(patientId);
	}

	/**
	 * Add a new medical record
	 * @param medicalRecord - Medical record object
	 * @return Saved medical record
	 */
	@PostMapping("/add")
	public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		medicalRecord.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		medicalRecord.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		return medicalRecordRepo.save(medicalRecord);
	}

	/**
	 * Update an existing medical record
	 * @param recordId - Medical record ID
	 * @param medicalRecord - Updated medical record data
	 * @return Updated medical record or 404 if not found
	 */
	@PutMapping("/update/{recordId}")
	public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable long recordId, 
			@RequestBody MedicalRecord medicalRecord) {
		Optional<MedicalRecord> existingRecord = medicalRecordRepo.findById(recordId);
		if (existingRecord.isPresent()) {
			MedicalRecord record = existingRecord.get();
			record.setVisitDate(medicalRecord.getVisitDate());
			record.setDiagnosis(medicalRecord.getDiagnosis());
			record.setSymptoms(medicalRecord.getSymptoms());
			record.setTreatment(medicalRecord.getTreatment());
			record.setPrescribedMedicines(medicalRecord.getPrescribedMedicines());
			record.setDoctorNotes(medicalRecord.getDoctorNotes());
			record.setFollowUpDate(medicalRecord.getFollowUpDate());
			record.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			
			return ResponseEntity.ok(medicalRecordRepo.save(record));
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Delete a medical record
	 * @param recordId - Medical record ID to delete
	 * @return Success response or 404 if not found
	 */
	@DeleteMapping("/delete/{recordId}")
	public ResponseEntity<Void> deleteMedicalRecord(@PathVariable long recordId) {
		if (medicalRecordRepo.existsById(recordId)) {
			medicalRecordRepo.deleteById(recordId);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	/**
	 * Get count of medical records for a patient
	 * @param patientId - Patient ID
	 * @return Total count of medical records
	 */
	@GetMapping("/patient/{patientId}/count")
	public int getMedicalRecordCount(@PathVariable long patientId) {
		return medicalRecordRepo.countRecordsByPatientId(patientId);
	}

	/**
	 * Search medical records by diagnosis
	 * @param diagnosis - Diagnosis keyword to search
	 * @return List of medical records matching diagnosis
	 */
	@GetMapping("/search/diagnosis/{diagnosis}")
	public List<MedicalRecord> searchByDiagnosis(@PathVariable String diagnosis) {
		return medicalRecordRepo.findByDiagnosis(diagnosis);
	}
}