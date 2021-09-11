package cgm.testdev.entity;

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
	

}
