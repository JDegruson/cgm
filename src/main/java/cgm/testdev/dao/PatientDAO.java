package cgm.testdev.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import cgm.testdev.entity.Patient;
import cgm.testdev.rowmapper.PatientRowMapper;

@Repository
public class PatientDAO extends JdbcTemplateForDAO implements IPatientDAO{
	
	private static final Logger logger = LogManager.getLogger(PatientDAO.class);
	
	@Autowired
	public NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public Patient getPatient(int id) {
		
		
		logger.info("getName with id");
		MapSqlParameterSource args = new MapSqlParameterSource();
		args.addValue("id", id);
		return this.jdbcTemplate.queryForObject("select * from Patient where id = :id", args, new PatientRowMapper());
	}

}
