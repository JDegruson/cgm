package cgm.testdev.dao;

import cgm.testdev.entity.Visit;

public interface IVisitDAO {


	/**
	 * Returns number of row created
	 */
	int createVisit(Visit visit);

	Visit getVisit(int id);

	int updateVisit(Visit visit);

}
