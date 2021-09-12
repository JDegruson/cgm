package cgm.testdev.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cgm.testdev.dto.PatientDTO;
import cgm.testdev.service.IPatientService;

@Controller
public class PatientController {
	private static final Logger logger = LogManager.getLogger(PatientController.class);

	@Autowired
	private IPatientService patientService;

	@GetMapping("/api/patient/{id}")
	public ResponseEntity<PatientDTO> getPatient(@PathVariable(value = "id") final int id) {
		logger.info("getPatient with id : {}", id);
		PatientDTO patientDTO = patientService.getPatient(id);
		return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
	}
}
