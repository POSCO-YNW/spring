package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.Post;
import pack01.dto.post.response.PostDepartmentResponse;
import pack01.dto.post.response.PostPagingResponse;
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

    public void update(Post post, Long postId) {
        String sql = "UPDATE post SET title = ?, updated_at = ?, start_date = ?, end_date = ?, description = ?, " +
                "admin_id = ?, department_id = ? WHERE post_id = ?";
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        Timestamp updatedAt = Timestamp.valueOf(LocalDateTime.now());
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setString(1, post.getTitle());
//            ps.setTimestamp(2, updatedAt);
//            ps.setDate(3, post.getStartDate());
//            ps.setDate(4, post.getEndDate());
//            ps.setString(5, post.getDescription());
//            ps.setLong(6, post.getAdminId());
//            ps.setLong(7, post.getDepartmentId());
//            ps.setLong(8, post.getPostId());
//            return ps;
//        }, keyHolder);
//
//        return Objects.requireNonNull(keyHolder.getKey()).longValue();
        jdbcTemplate.update(sql, post.getTitle(), post.getUpdatedAt(), post.getStartDate(), post.getEndDate(),
                post.getDescription(), post.getAdminId(), post.getDepartmentId(), postId);
    }

    public void updateEndDateSetDeadline(Long postId) {
        String sql = "update post set end_date = CURRENT_DATE where post_id = ?";
        jdbcTemplate.update(sql, postId);
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
        String sql = "SELECT * FROM post WHERE title LIKE ? ORDER BY CASE WHEN end_date > CURDATE() THEN 0 ELSE 1 END, end_date";
        return jdbcTemplate.query(sql, new PostMapper(), "%" + postTitle + "%");
    }

    public PostPagingResponse findPostAndDepartmentByPostTitle(String postTitle, Integer page) {
        int offset = page * 9;
        int limit = 9;

        String query = "SELECT * FROM post P JOIN department D ON P.department_id = D.department_id " +
                "WHERE P.title LIKE ? " +
                "ORDER BY CASE WHEN P.end_date > CURDATE() THEN 0 ELSE 1 END, P.end_date " +
                "LIMIT ?, ?";

        String countQuery = "SELECT COUNT(*) AS total_count FROM post P JOIN department D ON P.department_id = D.department_id " +
                "WHERE P.title LIKE ?";

        List<PostDepartmentResponse> results = jdbcTemplate.query(query, new PostDepartmentMapper(), "%" + postTitle + "%", offset, limit);
        int totalCount = jdbcTemplate.queryForObject(countQuery, Integer.class, "%" + postTitle + "%");

        return new PostPagingResponse(results, totalCount);
    }

    public PostPagingResponse findPostAndDepartmentByDepartmentTitle(String departmentName, Integer page) {
        int offset = page * 9;
        int limit = 9;

        String query = "SELECT * FROM post P JOIN department D ON P.department_id = D.department_id " +
                "WHERE D.name LIKE ? " +
                "ORDER BY CASE WHEN P.end_date > CURDATE() THEN 0 ELSE 1 END, P.end_date " +
                "LIMIT ?, ?";

        String countQuery = "SELECT COUNT(*) AS total_count FROM post P JOIN department D ON P.department_id = D.department_id " +
                "WHERE D.name LIKE ?";

        List<PostDepartmentResponse> results = jdbcTemplate.query(query, new PostDepartmentMapper(), "%" + departmentName + "%", offset, limit);
        int totalCount = jdbcTemplate.queryForObject(countQuery, Integer.class, "%" + departmentName + "%");

        return new PostPagingResponse(results, totalCount);
    }


    public List<Post> findByDepartmentName(String departmentName) {
        String sql = "SELECT * FROM post P join department D on P.department_id = D.department_id\n" +
                "WHERE D.name LIKE ? ORDER BY CASE WHEN P.end_date > CURDATE() THEN 0 ELSE 1 END, P.end_date";
        return jdbcTemplate.query(sql, new PostMapper(), "%" + departmentName + "%");
    }

    public List<Post> findByMyResume(Long id) {
        String sql = "select distinct * " +
                "from user " +
                "join resume rs on user.user_id = rs.applicant_id " +
                "join post ps on rs.post_id = ps.post_id " +
                "join department dp on ps.department_id = dp.department_id " +
                "where user.user_id = ? ORDER BY CASE WHEN ps.end_date > CURDATE() THEN 0 ELSE 1 END, ps.end_date";

        return jdbcTemplate.query(sql, new PostMapper(), id);
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

    private static class PostDepartmentMapper implements RowMapper<PostDepartmentResponse> {
        @Override
        public PostDepartmentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long postId = rs.getLong("post_id");
            String title = rs.getString("title");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            Date startDate = rs.getDate("start_date");
            Date endDate = rs.getDate("end_date");
            String description = rs.getString("description");
            Long adminId = rs.getLong("admin_id");
            Long departmentId = rs.getLong("department_id");
            String name = rs.getString("name");
            String telephoneNumber = rs.getString("telephone_number");
            String deptKey = rs.getString("dept_key");
            String location = rs.getString("location");
            double x = rs.getDouble("x");
            double y = rs.getDouble("y");
            return new PostDepartmentResponse(postId, title, createdAt, updatedAt, startDate, endDate, description, adminId, departmentId, name, telephoneNumber, deptKey, location, x, y);
        }
    }
}
