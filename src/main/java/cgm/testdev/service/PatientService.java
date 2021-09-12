package cgm.testdev.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgm.testdev.dao.IPatientDAO;
import cgm.testdev.dto.PatientDTO;
import cgm.testdev.entity.Patient;

@Service
public class PatientService implements IPatientService{
	
	private static final Logger logger = LogManager.getLogger(PatientService.class);
	
	@Autowired
	private IPatientDAO patientDAO;
	

	@Override
	public PatientDTO getPatient(int id) {
		logger.info("getPatient with id {}", id);
		
		ModelMapper modelMapper = new ModelMapper();

		Patient patient = this.patientDAO.getPatient(id);
		logger.info("Patient retrieved : {}", patient);
		
		PatientDTO patientDTO = modelMapper.map(patient, PatientDTO.class);
		logger.info("PatientDTO mapped : {}", patientDTO);

		return patientDTO;
	}

}
