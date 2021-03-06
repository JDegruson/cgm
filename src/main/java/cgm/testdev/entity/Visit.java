package cgm.testdev.entity;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.time.LocalDateTime;

import cgm.testdev.enums.ReasonOfVisit;
import cgm.testdev.enums.TypeOfVisit;

public class Visit {
	
	private int id;
	private LocalDateTime date;
	private TypeOfVisit type;
	private ReasonOfVisit reason;
	private String familyHistory;
	private Patient patient;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public TypeOfVisit getType() {
		return type;
	}
	public void setType(TypeOfVisit type) {
		this.type = type;
	}
	public ReasonOfVisit getReason() {
		return reason;
	}
	public void setReason(ReasonOfVisit reason) {
		this.reason = reason;
	}
	public String getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public String toString() {
		return reflectionToString(this);
	}

}
