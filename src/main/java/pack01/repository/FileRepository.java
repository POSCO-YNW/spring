package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.File;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class FileRepository {

    private final JdbcTemplate jdbcTemplate;

    public FileRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //    public void save(File file) {
//        String sql = "INSERT INTO files (link, post_id) VALUES (?, ?)";
//        jdbcTemplate.update(sql, file.getLink(), file.getPostId());
//    }
    public Long save(File file) {
        String sql = "INSERT INTO files (link, post_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, file.getLink());
            ps.setLong(2, file.getPostId());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void update(File file) {
        String sql = "UPDATE files SET link = ?, post_id = ? WHERE file_id = ?";
        jdbcTemplate.update(sql, file.getLink(), file.getPostId(), file.getFileId());
    }

    public void delete(Long fileId) {
        String sql = "DELETE FROM files WHERE file_id = ?";
        jdbcTemplate.update(sql, fileId);
    }

    public File findById(Long fileId) {
        String sql = "SELECT * FROM files WHERE file_id = ?";
        return jdbcTemplate.queryForObject(sql, new FileMapper(), fileId);
    }

    public List<File> findByPostId(Long postId) {
        String sql = "SELECT * FROM files WHERE post_id = ?";
        return jdbcTemplate.query(sql, new FileMapper(), postId);
    }

    private static class FileMapper implements RowMapper<File> {
        @Override
        public File mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long fileId = rs.getLong("file_id");
            String link = rs.getString("link");
            Long postId = rs.getLong("post_id");
            return new File(fileId, link, postId);
        }
    }

}
