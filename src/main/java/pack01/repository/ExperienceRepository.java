package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.Experience;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ExperienceRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExperienceRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Experience experience) {
        String sql = "INSERT INTO experience (company, period, work, resume_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, experience.getCompany());
            ps.setInt(2, experience.getPeriod());
            ps.setString(3, experience.getWork());
            ps.setLong(4, experience.getResumeId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Experience findById(Long experienceId) {
        String sql = "SELECT * FROM experience WHERE experience_id = ?";
        return jdbcTemplate.queryForObject(sql, new ExperienceMapper(), experienceId);
    }

    public List<Experience> findAll() {
        String sql = "SELECT * FROM experience";
        return jdbcTemplate.query(sql, new ExperienceMapper());
    }

    public void update(Experience experience) {
        String sql = "UPDATE experience SET company = ?, period = ?, work = ?, resume_id = ? WHERE experience_id = ?";
        jdbcTemplate.update(sql, experience.getCompany(), experience.getPeriod(), experience.getWork(), experience.getResumeId(), experience.getExperienceId());
    }

    public void delete(Long experienceId) {
        String sql = "DELETE FROM experience WHERE experience_id = ?";
        jdbcTemplate.update(sql, experienceId);
    }

    private static class ExperienceMapper implements RowMapper<Experience> {
        @Override
        public Experience mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long experienceId = rs.getLong("experience_id");
            String company = rs.getString("company");
            int period = rs.getInt("period");
            String work = rs.getString("work");
            Long resumeId = rs.getLong("resume_id");

            return new Experience(experienceId, company, period, work, resumeId);
        }
    }
}