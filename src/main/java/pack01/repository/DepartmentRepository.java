package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.Department;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class DepartmentRepository {
    private final JdbcTemplate jdbcTemplate;

    public DepartmentRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //    public void save(Department department) {
//        String sql = "INSERT INTO department (name, telephone_number, key, location, x, y) VALUES (?, ?, ?, ?, ?, ?)";
//        jdbcTemplate.update(sql, department.getName(), department.getTelephoneNumber(), department.getKey(), department.getLocation(), department.getX(), department.getY());
//    }
    public Long save(Department department) {
        String sql = "INSERT INTO department (name, telephone_number, dept_key, location, x, y) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, department.getName());
            ps.setString(2, department.getTelephoneNumber());
            ps.setString(3, department.getDeptKey());
            ps.setString(4, department.getLocation());
            ps.setDouble(5, department.getX());
            ps.setDouble(6, department.getY());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(Department department) {
        String sql = "UPDATE department SET name = ?, telephone_number = ?, dept_key = ?, location = ?, x = ?, y = ? WHERE department_id = ?";
        jdbcTemplate.update(sql, department.getName(), department.getTelephoneNumber(), department.getDeptKey(), department.getLocation(), department.getX(), department.getY(), department.getDepartmentId());
    }

    public void delete(Long departmentId) {
        String sql = "DELETE FROM department WHERE department_id = ?";
        jdbcTemplate.update(sql, departmentId);
    }

    public Department findById(Long departmentId) {
        String sql = "SELECT * FROM department WHERE department_id = ?";
        return jdbcTemplate.queryForObject(sql, new DepartmentRowMapper(), departmentId);
    }

    public List<Department> findByName(String departmentName) {
        String sql = "SELECT * FROM department WHERE name LIKE ?";
        return jdbcTemplate.query(sql, new DepartmentRowMapper(), "%" + departmentName + "%");
    }

    public List<Department> findAll() {
        String sql = "SELECT * FROM department";
        return jdbcTemplate.query(sql, new DepartmentRowMapper());
    }

    public void updateKeyById(Long deptId, String key) {
        String sql = "UPDATE department SET dept_key = ? WHERE department_id = ?";
        jdbcTemplate.update(sql, key, deptId);
    }

    public Department findByKey(String deptKey) {
        String sql = "SELECT * FROM department WHERE dept_key = ?";
        return jdbcTemplate.queryForObject(sql, new DepartmentRowMapper(), deptKey);
    }

    private static class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long departmentId = rs.getLong("department_id");
            String name = rs.getString("name");
            String telephoneNumber = rs.getString("telephone_number");
            String key = rs.getString("dept_key");
            String location = rs.getString("location");
            double x = rs.getDouble("x");
            double y = rs.getDouble("y");
            return new Department(departmentId, name, telephoneNumber, key, location, x, y);
        }
    }

}
