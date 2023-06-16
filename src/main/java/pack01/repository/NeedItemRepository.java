package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.NeedItem;
import pack01.dto.needitem.response.NeedItemResumeItem;
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
        String sql = "INSERT INTO need_item (title, post_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, needItem.getTitle());
            ps.setLong(2, needItem.getPostId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(NeedItem needItem) {
        String sql = "UPDATE need_item SET need_item_id = ?, title = ?, post_id = ? WHERE need_item_id = ?";
        jdbcTemplate.update(sql, needItem.getNeedItemId(), needItem.getTitle(), needItem.getPostId(), needItem.getNeedItemId());
    }

    public void delete(Long needItemId) {
        String sql = "DELETE FROM need_item WHERE need_item_id = ?";
        jdbcTemplate.update(sql, needItemId);
    }

    public NeedItem findByNeedItemId(Long needItemId) {
        String sql = "SELECT * FROM need_item WHERE need_item_id = ?";
        return jdbcTemplate.queryForObject(sql, new NeedItemMapper(), needItemId);
    }

    public List<NeedItem> findByPostId(Long postId) {
        String sql = "SELECT * FROM need_item WHERE post_id = ?";
        return jdbcTemplate.query(sql, new NeedItemMapper(), postId);
    }

    public List<NeedItemResumeItem> findBydResumeId(Long resumeId) {
        String sql = "SELECT * FROM need_item n join resume_item r on n.need_item_id = r.need_item_id WHERE resume_id = ?";
        return jdbcTemplate.query(sql, new NeedItemResumeItemMapper(), resumeId);
    }

    private static class NeedItemMapper implements RowMapper<NeedItem> {
        @Override
        public NeedItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long needItemId = rs.getLong("need_item_id");
            String title = rs.getString("title");
            Long postId = rs.getLong("post_id");
            return new NeedItem(needItemId, title, postId);
        }
    }

    private static class NeedItemResumeItemMapper implements RowMapper<NeedItemResumeItem> {
        @Override
        public NeedItemResumeItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long needItemId = rs.getLong("need_item_id");
            String title = rs.getString("title");
            Long postId = rs.getLong("post_id");
            Long resumeItemId = rs.getLong("resume_item_id");
            String description = rs.getString("description");
            Long resumeId = rs.getLong("resume_id");
            return new NeedItemResumeItem(needItemId, title, postId, resumeItemId, description, resumeId);
        }
    }

}
