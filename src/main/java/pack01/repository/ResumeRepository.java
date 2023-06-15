package pack01.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.Education;
import pack01.domain.Resume;
import pack01.domain.type.ResumeStatusType;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ResumeRepository {

    private JdbcTemplate jdbcTemplate;

    public ResumeRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Resume resume) {
        String sql = "INSERT INTO resume (resume_id, applicant_id, post_id, department_id, status, description) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, resume.getResumeId());
            ps.setLong(2, resume.getApplicantId());
            ps.setLong(3, resume.getPostId());
            ps.setLong(4, resume.getDepartmentId());
            ps.setString(5, resume.getStatus().toString());
            ps.setString(6, resume.getDescription());

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Resume findById(Long resumeId) {
        String sql = "SELECT * FROM resume WHERE resume_id = ?";
        return jdbcTemplate.queryForObject(sql, new ResumeMapper(), resumeId);
    }

    public List<Resume> findAll() {
        String sql = "SELECT * FROM resume";
        return jdbcTemplate.query(sql, new ResumeMapper());
    }

    public void update(Resume resume) {
        String sql = "UPDATE resume SET applicant_id = ?, post_id = ?, department_id = ?, " +
                "status = ?, description = ? WHERE resume_id = ?";

        jdbcTemplate.update(sql, resume.getApplicantId(), resume.getPostId(), resume.getDepartmentId(),
                resume.getStatus(), resume.getDescription(), resume.getResumeId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM resume WHERE resume_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class ResumeMapper implements RowMapper<Resume> {
        @Override
        public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long resumeId = rs.getLong("resume_id");
            Long applicantId = rs.getLong("applicant_id");
            Long postId = rs.getLong("post_id");
            Long departmentId = rs.getLong("department_id");
            ResumeStatusType status = ResumeStatusType.valueOf(rs.getString("status"));
            String description = rs.getString("description");

            return new Resume(resumeId, applicantId, postId, departmentId, status, description);
        }
    }
}
