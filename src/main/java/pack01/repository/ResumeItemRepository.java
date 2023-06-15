package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.ResumeItem;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ResumeItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public ResumeItemRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(ResumeItem resumeItem) {
        String sql = "INSERT INTO resume_item (resume_item_id, description, need_item_id, resume_id) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, resumeItem.getResumeItemId());
            ps.setString(2, resumeItem.getDescription());
            ps.setLong(3, resumeItem.getNeedItemId());
            ps.setLong(4, resumeItem.getResumeId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public ResumeItem findById(Long resumeItemId) {
        String sql = "SELECT * FROM resume_item WHERE resume_item_id = ?";
        return jdbcTemplate.queryForObject(sql, new ResumeItemMapper(), resumeItemId);
    }

    public List<ResumeItem> findAll() {
        String sql = "SELECT * FROM resume_item";
        return jdbcTemplate.query(sql, new ResumeItemMapper());
    }

    public void update(ResumeItem resumeItem) {
        String sql = "UPDATE resume_item SET description = ?, need_item_id = ?, resume_id = ? " +
                "WHERE resume_item_id = ?";

        jdbcTemplate.update(sql, resumeItem.getDescription(), resumeItem.getNeedItemId(),
                resumeItem.getResumeId(), resumeItem.getResumeItemId());
    }

    public void delete(Long resumeItemId) {
        String sql = "DELETE FROM resume_item WHERE resume_item_id = ?";
        jdbcTemplate.update(sql, resumeItemId);
    }

    private static class ResumeItemMapper implements RowMapper<ResumeItem> {
        public ResumeItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long resumeItemId = rs.getLong("resume_item_id");
            String description = rs.getString("description");
            Long needItemId = rs.getLong("need_item_id");
            Long resumeId = rs.getLong("resume_id");

            return new ResumeItem(resumeItemId, description, needItemId, resumeId);
        }
    }
}
