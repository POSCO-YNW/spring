package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.NeedItem;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class NeedItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public NeedItemRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //    public void save(NeedItem needItem) {
//        String sql = "INSERT INTO need_items (resume_item, title, post_id) VALUES (?, ?, ?)";
//        jdbcTemplate.update(sql, needItem.getResumeItem(), needItem.getTitle(), needItem.getPostId());
//    }
    public Long save(NeedItem needItem) {
        String sql = "INSERT INTO need_item (resume_item_id, title, post_id) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, needItem.getResumeItemId());
            ps.setString(2, needItem.getTitle());
            ps.setLong(3, needItem.getPostId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(NeedItem needItem) {
        String sql = "UPDATE need_item SET resume_item_id = ?, title = ?, post_id = ? WHERE resume_item_id = ?";
        jdbcTemplate.update(sql, needItem.getResumeItemId(), needItem.getTitle(), needItem.getPostId(), needItem.getResumeItemId());
    }

    public void delete(Long resumeItemId) {
        String sql = "DELETE FROM need_item WHERE resume_item_id = ?";
        jdbcTemplate.update(sql, resumeItemId);
    }

    public NeedItem findByResumeItem(Long resumeItemId) {
        String sql = "SELECT * FROM need_item WHERE resume_item_id = ?";
        return jdbcTemplate.queryForObject(sql, new NeedItemMapper(), resumeItemId);
    }

    public List<NeedItem> findByPostId(Long postId) {
        String sql = "SELECT * FROM need_item WHERE post_id = ?";
        return jdbcTemplate.query(sql, new NeedItemMapper(), postId);
    }

    private static class NeedItemMapper implements RowMapper<NeedItem> {
        @Override
        public NeedItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long resumeItemId = rs.getLong("need_item_id");
            String title = rs.getString("title");
            Long postId = rs.getLong("post_id");
            return new NeedItem(resumeItemId, title, postId);
        }
    }

}
