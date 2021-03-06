package cgm.testdev.entity;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

import java.time.LocalDate;

public class Patient {
	
	private int id;
	private String name;
	private String surname;
	private LocalDate birthDate;
	private String socialSecurityNumber;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	
	public String toString() {
		return reflectionToString(this);
	}
}
