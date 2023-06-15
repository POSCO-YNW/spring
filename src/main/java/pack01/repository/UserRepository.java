package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.User;
import pack01.domain.type.RoleType;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(User user) {
        String sql = "INSERT INTO user (username, password, email, birthday, role, address, created_at, updated_at, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setObject(4, user.getBirthday());
            ps.setString(5, user.getRole().toString());
            ps.setString(6, user.getAddress());
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
            ps.setLong(9, user.getDepartmentId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public User findById(Long userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(), userId);
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(), email);
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? and password = ?";
        List<User> users = jdbcTemplate.query(sql, new UserMapper(), email, password);
        return users.isEmpty() ? null : users.get(0);
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void update(User user) {
        String sql = "UPDATE user SET username = ?, password = ?, email = ?, birthday = ?, role = ?, address = ?, updated_at = ?, department_id = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getBirthday(),
                user.getRole().toString(), user.getAddress(), Timestamp.valueOf(LocalDateTime.now()), user.getDepartmentId(), user.getUserId());
    }

    public void delete(Long userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    private static class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long userId = rs.getLong("user_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            Date birthday = rs.getDate("birthday");
            RoleType role = RoleType.valueOf(rs.getString("role"));
            String address = rs.getString("address");
            Timestamp createdAt = rs.getTimestamp("created_at");
            Timestamp updatedAt = rs.getTimestamp("updated_at");
            Long departmentId = rs.getLong("department_id");

            return new User(userId, username, password, email, birthday, role, address, createdAt, updatedAt, departmentId);
        }
    }
}