package cgm.testdev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgm.testdev.dao.IPatientDAO;
import cgm.testdev.dto.PatientDTO;
import cgm.testdev.entity.Patient;

@Service
public class PatientService implements IPatientService{
	
	@Autowired IPatientDAO patientDAO;
	
	@Override
	public PatientDTO getPatient(int id) {
		PatientDTO patientDTO = new PatientDTO();
		
		
		Patient patient = patientDAO.getPatient(id);
		
		patientDTO.setId(patient.getId());
		patientDTO.setName(patient.getName());
		patientDTO.setSocialSecurityNumber(patient.getSocialSecurityNumber());
		patientDTO.setSurname(patient.getSurname());
		patientDTO.setBirthDate(patient.getBirthDate());

		return patientDTO;
	}

}
