package pack01.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.File;
import pack01.domain.Post;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;
    public PostRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
//    @Autowired
//    public PostRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    public void save(Post post) {
//        String sql = "INSERT INTO post (title, created_at, updated_at, start_date, end_date, description, admin_id, department_id) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, post.getTitle(), post.getCreatedAt(), post.getUpdatedAt(), post.getStartDate(),
//                        post.getEndDate(), post.getDescription(), post.getAdminId(), post.getDepartmentId());
//    }
    public Long save(Post post) {
        String sql = "INSERT INTO post (title, created_at, updated_at, start_date, end_date, description, admin_id, department_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getTitle());
            ps.setTimestamp(2, post.getCreatedAt());
            ps.setTimestamp(3, post.getUpdatedAt());
            ps.setDate(4, post.getStartDate());
            ps.setDate(5, post.getEndDate());
            ps.setString(6, post.getDescription());
            ps.setLong(7, post.getAdminId());
            ps.setLong(8, post.getDepartmentId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(Post post) {
        String sql = "UPDATE post SET title = ?, updated_at = ?, start_date = ?, end_date = ?, description = ?, " +
                "admin_id = ?, department_id = ? WHERE post_id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getUpdatedAt(), post.getStartDate(), post.getEndDate(),
                post.getDescription(), post.getAdminId(), post.getDepartmentId(), post.getPostId());
    }

    public void delete(Long postId) {
        String sql = "DELETE FROM post WHERE post_id = ?";
        jdbcTemplate.update(sql, postId);
    }

    public Post findById(Long postId) {
        String sql = "SELECT * FROM post WHERE post_id = ?";
        return jdbcTemplate.queryForObject(sql, new PostMapper(), postId);
    }
    public List<Post> findByTitle(String postTitle) {
        String sql = "SELECT * FROM post WHERE title LIKE ?";
        return jdbcTemplate.query(sql, new PostMapper(), "%" + postTitle + "%");
    }
    public List<Post> findByDepartmentName(String departmentName) {
        String sql = "SELECT * FROM post P join department D on P.department_id = D.department_id\n" +
                "WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new PostMapper(), "%" + departmentName + "%");
    }

    public List<Post> findByMyResume(Long id) {
        String sql = "select * " +
                "from user " +
                "join resume rs on user.user_id = rs.applicant_id " +
                "join post ps on rs.post_id = ps.post_id " +
                "join department dp on ps.department_id = dp.department_id " +
                "where user.user_id = ?";

        return jdbcTemplate.query(sql, new PostMapper());
    }

    public List<Post> findAll() {
        String sql = "SELECT * FROM post";
        return jdbcTemplate.query(sql, new PostMapper());
    }
    private static class PostMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long postId = rs.getLong("post_id");
            String title = rs.getString("title");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            String description = rs.getString("description");
            Long adminId = rs.getLong("admin_id");
            Long departmentId = rs.getLong("department_id");
            return new Post(postId, title, createdAt, updatedAt, startDate, endDate, description, adminId, departmentId);
        }
    }
}
