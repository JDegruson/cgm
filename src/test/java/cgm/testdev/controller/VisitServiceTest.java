package cgm.testdev.controller;

import static cgm.testdev.enums.ReasonOfVisit.FIRST_VISIT;
import static cgm.testdev.enums.TypeOfVisit.HOME;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import cgm.testdev.dao.IVisitDAO;
import cgm.testdev.dto.VisitDTO;
import cgm.testdev.entity.Patient;
import cgm.testdev.entity.Visit;
import cgm.testdev.service.VisitService;

@RunWith(MockitoJUnitRunner.class)
@ComponentScan
@ContextConfiguration(classes = VisitServiceTest.class)
public class VisitServiceTest {
	
	@InjectMocks
	private VisitService visitService;

	@Mock
	private IVisitDAO visitDAOMock;
	
	@Test
	public void createVisitCheckMapper() {
		final ArgumentCaptor<Visit> captor = ArgumentCaptor.forClass(Visit.class);
		when(visitDAOMock.createVisit(Mockito.any())).thenReturn(Mockito.anyInt());
		VisitDTO visitDTO = new VisitDTO();
		visitDTO.setDate(LocalDateTime.now());
		visitDTO.setFamilyHistory("TestFamilyHistory");
		Patient patient = new Patient();
		patient.setId(1);
		visitDTO.setPatient(patient);
		visitDTO.setReason(FIRST_VISIT);
		visitDTO.setType(HOME);
		
		visitService.createVisit(visitDTO);
		
		verify(visitDAOMock).createVisit(captor.capture());

		final Visit visit = captor.getValue();
		
		assertThat(visit.getFamilyHistory(), is("TestFamilyHistory"));
		assertThat(visit.getDate(), is(visitDTO.getDate()));
		assertThat(visit.getPatient().getId(), is(1));
		assertThat(visit.getReason(), is(FIRST_VISIT));
		assertThat(visit.getType(), is(HOME));
	}

}
