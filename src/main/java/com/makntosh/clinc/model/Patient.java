package com.makntosh.clinc.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pid;
	@Column(name = "patient_fname")
	private String patientFname;
	@Column(name = "patient_lname")
	private String patientLname;
	@Column(name = "age")
	private int age;
	@Column(name = "gender")
	private String gender;
	@Column(name = "pdate")
	private Date pdate;
	@Column(name = "symptoms")
	private String symptoms;
	@Column(name = "medicines")
	private String medicines;
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Patient(long pid, String patientFname, String patientLname, int age, Date pdate, String symptoms,
			String medicines) {
		super();
		this.pid = pid;
		this.patientFname = patientFname;
		this.patientLname = patientLname;
		this.age = age;
		this.pdate = pdate;
		this.symptoms = symptoms;
		this.medicines = medicines;
	}
	public long getPid() {
		return pid;
	}
	public void setPid(long pid) {
		this.pid = pid;
	}
	public String getPatientFname() {
		return patientFname;
	}
	public void setPatientFname(String patientFname) {
		this.patientFname = patientFname;
	}
	public String getPatientLname() {
		return patientLname;
	}
	public void setPatientLname(String patientLname) {
		this.patientLname = patientLname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getMedicines() {
		return medicines;
	}
	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
}
