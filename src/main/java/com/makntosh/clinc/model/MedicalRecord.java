package com.makntosh.clinc.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medical_record")
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long recordId;
	
	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;
	
	@Column(name = "visit_date")
	private Date visitDate;
	
	@Column(name = "diagnosis", length = 1000)
	private String diagnosis;
	
	@Column(name = "symptoms", length = 1000)
	private String symptoms;
	
	@Column(name = "treatment", length = 1000)
	private String treatment;
	
	@Column(name = "prescribed_medicines", length = 1000)
	private String prescribedMedicines;
	
	@Column(name = "doctor_notes", length = 2000)
	private String doctorNotes;
	
	@Column(name = "follow_up_date")
	private Date followUpDate;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;

	public MedicalRecord() {
		super();
	}

	public MedicalRecord(Patient patient, Date visitDate, String diagnosis, String symptoms, String treatment,
			String prescribedMedicines, String doctorNotes, Date followUpDate) {
		super();
		this.patient = patient;
		this.visitDate = visitDate;
		this.diagnosis = diagnosis;
		this.symptoms = symptoms;
		this.treatment = treatment;
		this.prescribedMedicines = prescribedMedicines;
		this.doctorNotes = doctorNotes;
		this.followUpDate = followUpDate;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(String prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}

	public String getDoctorNotes() {
		return doctorNotes;
	}

	public void setDoctorNotes(String doctorNotes) {
		this.doctorNotes = doctorNotes;
	}

	public Date getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(Date followUpDate) {
		this.followUpDate = followUpDate;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}