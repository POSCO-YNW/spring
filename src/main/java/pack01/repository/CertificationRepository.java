package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.Certification;
import pack01.domain.Education;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class CertificationRepository {
    private final JdbcTemplate jdbcTemplate;

    public CertificationRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Certification certification) {
        String sql = "INSERT INTO certification (name, level, date, resume_id) " +
                "VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,certification.getName());
            ps.setString(2, certification.getLevel());
            ps.setDate(3, certification.getDate());
            ps.setLong(4,certification.getResumeId());

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Certification findById(Long certificationId) {
        String sql = "SELECT * FROM certification WHERE certification_id = ?";
        return jdbcTemplate.queryForObject(sql, new CertificationMapper(), certificationId);
    }

    public List<Certification> findAll() {
        String sql = "SELECT * FROM certification";
        return jdbcTemplate.query(sql, new CertificationMapper());
    }

    public void update(Certification certification) {
        String sql = "UPDATE certification SET name = ?, level = ?, date = ?, resume_id = ? " +
                "WHERE certification_id = ?";
        jdbcTemplate.update(sql, certification.getName(), certification.getLevel(), certification.getDate(),
                certification.getResumeId(), certification.getCertificationId());
    }

    public void delete(Long certificationId) {
        String sql = "DELETE FROM certification WHERE certification_id = ?";
        jdbcTemplate.update(sql, certificationId);
    }

    private static class CertificationMapper implements RowMapper<Certification> {
        @Override
        public Certification mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long certificationId = rs.getLong("certification_id");
            String name = rs.getString("name");
            String level = rs.getString("level");
            Date date = rs.getDate("date");
            Long resumeId = rs.getLong("resume_id");

            return new Certification(certificationId, name, level, date, resumeId);
        }
    }








}
