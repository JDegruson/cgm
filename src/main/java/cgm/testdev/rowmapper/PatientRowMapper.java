package cgm.testdev.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cgm.testdev.entity.Patient;

public class PatientRowMapper implements RowMapper<Patient> {
	    @Override
	    public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Patient patient = new Patient();

	    	patient.setId(rs.getInt("id"));
	    	patient.setName(rs.getString("name"));
	    	patient.setSurname(rs.getString("surname"));
	    	patient.setSocialSecurityNumber(rs.getString("social_security_number"));
	    	patient.setBirthDate(rs.getDate("birth_date").toLocalDate());

	        return patient;
	    }
}
