package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pack01.domain.SocialAccount;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class SocialAccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public SocialAccountRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long save(SocialAccount socialAccount) {
        String sql = "INSERT INTO social_account (social_account_id, type, account_id, link, user_id) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, socialAccount.getType());
            ps.setString(2, socialAccount.getAccountId());
            ps.setString(3, socialAccount.getLink());
            ps.setLong(4, socialAccount.getUserId());

            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public SocialAccount findById(Long socialAccountId) {
        String sql = "SELECT * FROM social_account WHERE social_account_id = ?";
        return jdbcTemplate.queryForObject(sql, new SocialAccountMapper(), socialAccountId);
    }

    public List<SocialAccount> findAll() {
        String sql = "SELECT * FROM social_account";
        return jdbcTemplate.query(sql, new SocialAccountMapper());
    }

    public void update(SocialAccount socialAccount) {
        String sql = "UPDATE social_account SET type = ?, account_id = ?, link = ?, user_id = ? " +
                "WHERE social_account_id = ?";
        jdbcTemplate.update(sql, socialAccount.getType(), socialAccount.getAccountId(), socialAccount.getLink(),
                socialAccount.getUserId(), socialAccount.getSocialAccountId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM social_account WHERE social_account_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<SocialAccount> findByUserId(Long userId) {
        String sql = "SELECT * FROM social_account where user_id = ?";
        return jdbcTemplate.query(sql, new SocialAccountMapper(), userId);
    }

    private static class SocialAccountMapper implements RowMapper<SocialAccount> {
        @Override
        public SocialAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long socialAccountId = rs.getLong("social_account_id");
            String type = rs.getString("type");
            String accountId = rs.getString("account_id");
            String link = rs.getString("link");
            Long userId = rs.getLong("user_id");

            return new SocialAccount(socialAccountId, type, accountId, link, userId);
        }
    }

}
