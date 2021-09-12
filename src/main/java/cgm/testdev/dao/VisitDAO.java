package cgm.testdev.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cgm.testdev.entity.Visit;
import cgm.testdev.rowmapper.VisitRowMapper;

@Repository
public class VisitDAO extends JdbcTemplateForDAO implements IVisitDAO {
	
	private static final Logger logger = LogManager.getLogger(VisitDAO.class);
	
	@Autowired
	public NamedParameterJdbcTemplate jdbcTemplate;
	
	
	/**
	 * Returns number of row created
	 */
	@Override
	public int createVisit(Visit visit) {
		logger.info("createVisit with idPatient : {} and visit : {}", visit.getPatient().getId(), visit);
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO VISIT (TYPE, REASON, DATE, FAMILY_HISTORY, PATIENT_ID) ");
		query.append("VALUES (CAST(:type AS type_of_visit),CAST(:reason AS reason_of_visit),:date,:familyHistory,:idPatient)");
		
		MapSqlParameterSource args = new MapSqlParameterSource();
		args.addValue("type", visit.getType().name());
		args.addValue("reason", visit.getReason().name());
		args.addValue("date", visit.getDate());
		args.addValue("familyHistory", visit.getFamilyHistory());
		args.addValue("idPatient", visit.getPatient().getId());
		
		return jdbcTemplate.update(query.toString(), args);
	}


	@Override
	public Visit getVisit(int id) {
		logger.info("getVisit with id : {}", id);
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT V.ID, V.DATE, V.FAMILY_HISTORY, V.REASON, V.TYPE, ");
		query.append(" P.ID PATIENT_ID, P.NAME PATIENT_NAME, P.SURNAME PATIENT_SURNAME, ");
		query.append("P.BIRTH_DATE PATIENT_BIRTH_DATE, P.SOCIAL_SECURITY_NUMBER PATIENT_SOCIAL_SECURITY_NUMBER ");
		query.append("FROM VISIT V LEFT JOIN PATIENT P ON V.PATIENT_ID = P.ID ");
		query.append("WHERE V.ID = :id");
		
		MapSqlParameterSource args = new MapSqlParameterSource();
		args.addValue("id", id);
		return this.jdbcTemplate.queryForObject(query.toString(), args, new VisitRowMapper());
	}
	
	@Override
	public int updateVisit(Visit visit) {
		logger.info("updateVisit with visit : {}", visit);
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE VISIT ");
		query.append(" SET DATE = :date, FAMILY_HISTORY = :familyHistory, REASON = CAST(:reason AS reason_of_visit), TYPE = CAST(:type AS type_of_visit), PATIENT_ID = :patientId ");
		query.append("WHERE ID = :id");
		
		MapSqlParameterSource args = new MapSqlParameterSource();
		args.addValue("id", visit.getId());
		args.addValue("date", visit.getDate());
		args.addValue("familyHistory", visit.getFamilyHistory());
		args.addValue("reason", visit.getReason().name());
		args.addValue("type", visit.getType().name());
		if(visit.getPatient()!=null) {
			args.addValue("patientId", visit.getPatient().getId());
		}
		return this.jdbcTemplate.update(query.toString(), args);
		
	}

}
