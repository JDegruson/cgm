package cgm.testdev.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cgm.testdev.dto.VisitDTO;
import cgm.testdev.service.IVisitService;

@Controller
public class VisitController {
	private static final Logger logger = LogManager.getLogger(VisitController.class);

	@Autowired
	private IVisitService visitService;

	/**
	 * Create visit in database. Date, patient id, reason and type must be provided
	 * 
	 * @param visitDTO
	 * @return visit created
	 */
	@PostMapping("/api/createVisit")
	public ResponseEntity<String> createVisit(@RequestBody VisitDTO visitDTO) {
		logger.info("createVisit with visitDTO : {}", visitDTO);

		HttpStatus status = BAD_REQUEST;
		if (checkCreateVisitParameters(visitDTO)) {
			visitService.createVisit(visitDTO);
			status = OK;
		}
		return new ResponseEntity<String>(status);
	}

	@GetMapping("/api/visit/{id}")
	public ResponseEntity<VisitDTO> getVisit(@PathVariable(value = "id") final int id) {
		logger.info("getVisit with id : {}", id);

		VisitDTO visitDTO = new VisitDTO();
		HttpStatus status = BAD_REQUEST;
		try {
			visitDTO = visitService.getVisit(id);
			status = OK;
		} catch (EmptyResultDataAccessException e) {
			status = HttpStatus.NOT_FOUND;
		}
		return ResponseEntity.status(status).body(visitDTO);
	}

	@PutMapping("/api/updateVisit")
	public HttpStatus updateVisit(@RequestBody VisitDTO visitDTO) {
		logger.info("updateVisit with visitDTO : {}", visitDTO);

		HttpStatus status = BAD_REQUEST;
		if (visitDTO != null && visitDTO.getId() != 0) {
			visitService.updateVisit(visitDTO);
			status = OK;
		}
		return status;
	}

	private boolean checkCreateVisitParameters(VisitDTO visitDTO) {
		return visitDTO != null && visitDTO.getDate() != null && visitDTO.getPatient() != null
				&& visitDTO.getPatient().getId() != 0 && visitDTO.getReason() != null && visitDTO.getType() != null;
	}
}
