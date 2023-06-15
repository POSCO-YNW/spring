package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.Skill;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class SkillRepository {
    private final JdbcTemplate jdbcTemplate;

    public SkillRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(Skill skill) {
        String sql = "INSERT INTO skill (stack, level, resume_id) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, skill.getStack());
            ps.setString(2, skill.getLevel());
            ps.setLong(3, skill.getResumeId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Skill findById(Long skillId) {
        String sql = "SELECT * FROM skill WHERE skill_id = ?";
        return jdbcTemplate.queryForObject(sql, new SkillMapper(), skillId);
    }

    public List<Skill> findAll() {
        String sql = "SELECT * FROM skill";
        return jdbcTemplate.query(sql, new SkillMapper());
    }

    public void update(Skill skill) {
        String sql = "UPDATE skill SET stack = ?, level = ?, resume_id = ? WHERE skill_id = ?";
        jdbcTemplate.update(sql, skill.getStack(), skill.getLevel(), skill.getResumeId(), skill.getSkillId());
    }

    public void delete(Long skillId) {
        String sql = "DELETE FROM skill WHERE skill_id = ?";
        jdbcTemplate.update(sql, skillId);
    }

    private static class SkillMapper implements RowMapper<Skill> {
        @Override
        public Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long skillId = rs.getLong("skill_id");
            String stack = rs.getString("stack");
            String level = rs.getString("level");
            Long resumeId = rs.getLong("resume_id");

            return new Skill(skillId, stack, level, resumeId);
        }
    }
}