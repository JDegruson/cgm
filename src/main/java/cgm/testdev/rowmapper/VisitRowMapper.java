package cgm.testdev.rowmapper;

import static cgm.testdev.enums.ReasonOfVisit.valueOf;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import cgm.testdev.entity.Patient;
import cgm.testdev.entity.Visit;
import cgm.testdev.enums.TypeOfVisit;

public class VisitRowMapper implements RowMapper<Visit>{
	@Override
    public Visit mapRow(ResultSet rs, int rowNum) throws SQLException {
		Visit visit = new Visit();
		Patient patient = new Patient();

		visit.setId(rs.getInt("id"));
		visit.setDate(rs.getTimestamp("date").toLocalDateTime());
		visit.setFamilyHistory(rs.getString("family_history"));
		visit.setReason(valueOf(rs.getString("reason")));
		visit.setType(TypeOfVisit.valueOf(rs.getString("type")));
		patient.setId(rs.getInt("patient_id"));
		patient.setName(rs.getString("patient_name"));
    	patient.setSurname(rs.getString("patient_surname"));
    	patient.setSocialSecurityNumber(rs.getString("patient_social_security_number"));
    	patient.setBirthDate(rs.getDate("patient_birth_date").toLocalDate());
		
		visit.setPatient(patient);

        return visit;
    }

}
