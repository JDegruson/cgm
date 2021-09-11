package cgm.testdev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cgm.testdev.dto.PatientDTO;
import cgm.testdev.service.IPatientService;

@Controller
public class PatientController {
	
	@Autowired
	public IPatientService patientService;

	@GetMapping("/api/getPatient/{id}")
	public PatientDTO getPatient(@PathVariable(value="id") final int id) {
		//TODO verification
		return patientService.getPatient(id);
	}
}
