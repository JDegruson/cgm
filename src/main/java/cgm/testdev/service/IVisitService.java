package cgm.testdev.service;

import cgm.testdev.dto.VisitDTO;

public interface IVisitService {

	void createVisit(VisitDTO visitDTO);

	VisitDTO getVisit(int id);

	VisitDTO updateVisit(VisitDTO visitDTO);

}
