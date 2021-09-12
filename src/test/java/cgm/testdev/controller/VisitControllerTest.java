package cgm.testdev.controller;

import static cgm.testdev.enums.ReasonOfVisit.FIRST_VISIT;
import static cgm.testdev.enums.TypeOfVisit.HOME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import cgm.testdev.dto.VisitDTO;
import cgm.testdev.entity.Patient;
import cgm.testdev.service.IVisitService;


@RunWith(MockitoJUnitRunner.class)
@ComponentScan
@ContextConfiguration(classes = VisitControllerTest.class)
public class VisitControllerTest {
	@InjectMocks
	private VisitController visitController;

	@Mock
	private IVisitService visitServiceMock;

	@Test
	public void createVisitWithDateNull() {
		VisitDTO visit = new VisitDTO();

		ResponseEntity<String> responseEntity = visitController.createVisit(visit);

		assertThat(responseEntity.getStatusCode(), equalTo(BAD_REQUEST));
	}
	
	@Test
	public void createVisitWithWrongPatientId() {
		VisitDTO visit = new VisitDTO();
		visit.setDate(LocalDateTime.now());
		visit.setFamilyHistory("TestFamilyHistory");
		Patient patient = new Patient();
		patient.setId(0);
		visit.setPatient(patient);
		visit.setReason(FIRST_VISIT);
		visit.setType(HOME);
		
		
		ResponseEntity<String> responseEntity = visitController.createVisit(visit);

		assertThat(responseEntity.getStatusCode(), equalTo(BAD_REQUEST));
	}
	
	@Test
	public void createVisitWithCorrectData() {
		VisitDTO visit = new VisitDTO();
		visit.setDate(LocalDateTime.now());
		visit.setFamilyHistory("TestFamilyHistory");
		Patient patient = new Patient();
		patient.setId(1);
		visit.setPatient(patient);
		visit.setReason(FIRST_VISIT);
		visit.setType(HOME);

		ResponseEntity<String> responseEntity = visitController.createVisit(visit);

		assertThat(responseEntity.getStatusCode(), equalTo(OK));
	}
	
	@Test
	public void getVisitWithIdNotFound() {
		when(visitServiceMock.getVisit(1)).thenThrow(EmptyResultDataAccessException.class);
		
		ResponseEntity<VisitDTO> responseEntity = visitController.getVisit(1);
		
		assertThat(responseEntity.getStatusCode(), is(NOT_FOUND));
	}
	
	@Test
	public void getVisitWithIdFound() {
		VisitDTO visit = new VisitDTO();
		visit.setDate(LocalDateTime.now());
		visit.setFamilyHistory("TestFamilyHistory");
		Patient patient = new Patient();
		patient.setId(1);
		visit.setPatient(patient);
		visit.setReason(FIRST_VISIT);
		visit.setType(HOME);
		
		when(visitServiceMock.getVisit(1)).thenReturn(visit);
		
		ResponseEntity<VisitDTO> responseEntity = visitController.getVisit(1);
		
		assertThat(responseEntity.getStatusCode(), is(OK));
		assertThat(responseEntity.getBody().getFamilyHistory(), is("TestFamilyHistory"));
	}
	
	@Test
	public void updateVisitWithVisitNull() {
		HttpStatus httpStatus = visitController.updateVisit(null);

		assertThat(httpStatus, equalTo(BAD_REQUEST));
	}
	
	@Test
	public void updateVisitWithWrongVisitId() {
		VisitDTO visit = new VisitDTO();
		visit.setId(0);
		HttpStatus httpStatus = visitController.updateVisit(visit);

		assertThat(httpStatus, equalTo(BAD_REQUEST));
	}
	
	@Test
	public void updateVisitWithCorrectVisitId() {
		VisitDTO visit = new VisitDTO();
		visit.setId(1);
		HttpStatus httpStatus = visitController.updateVisit(visit);

		assertThat(httpStatus, equalTo(OK));
	}

}
