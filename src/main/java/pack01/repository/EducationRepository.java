package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.Education;
import pack01.domain.type.EducationType;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class EducationRepository {
    private final JdbcTemplate jdbcTemplate;

    public EducationRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Education education) {
        String sql = "INSERT INTO education (type, school, grade, resume_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, education.getType().toString());
            ps.setString(2, education.getSchool());
            ps.setInt(3, education.getGrade());
            ps.setLong(4, education.getResumeId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Education findById(Long educationId) {
        String sql = "SELECT * FROM education WHERE education_id = ?";
        return jdbcTemplate.queryForObject(sql, new EducationMapper(), educationId);
    }

    public List<Education> findAll() {
        String sql = "SELECT * FROM education";
        return jdbcTemplate.query(sql, new EducationMapper());
    }

    public void update(Education education) {
        String sql = "UPDATE education SET type = ?, school = ?, grade = ?, resume_id = ? WHERE education_id = ?";
        jdbcTemplate.update(sql, education.getType().toString(),
                education.getSchool(), education.getGrade(), education.getResumeId(),
                education.getEducationId());
    }

    public void delete(Long educationId) {
        String sql = "DELETE FROM education WHERE education_id = ?";
        jdbcTemplate.update(sql, educationId);
    }

    public List<Education> findByResumeId(Long resumeId) {
        String sql = "SELECT * FROM education where resume_id = ?";
        return jdbcTemplate.query(sql, new EducationMapper(),resumeId);
    }

    private static class EducationMapper implements RowMapper<Education> {
        @Override
        public Education mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long educationId = rs.getLong("education_id");
            EducationType type = EducationType.valueOf(rs.getString("type"));
            String school = rs.getString("school");
            int grade = rs.getInt("grade");
            Long resumeId = rs.getLong("resume_id");

            return new Education(educationId, type, school, grade, resumeId);
        }
    }
}