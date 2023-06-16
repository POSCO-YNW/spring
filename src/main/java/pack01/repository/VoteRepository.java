package pack01.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;
import pack01.domain.Vote;
import pack01.repository.db.ConnectionManager;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VoteRepository {
    private final JdbcTemplate jdbcTemplate;

    public VoteRepository() {
        DataSource dataSource = ConnectionManager.getDataSource();
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Vote vote) {
        String sql = "INSERT INTO vote (vote, user_id, resume_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, vote.getVote(), vote.getUserId(), vote.getResumeId());
    }

    public void update(Vote vote) {
        String sql = "UPDATE vote SET vote = ?, user_id = ?, resume_id = ? WHERE vote_id = ?";
        jdbcTemplate.update(sql, vote.getVote(), vote.getUserId(), vote.getResumeId(), vote.getVoteId());
    }

    public void delete(Long voteId) {
        String sql = "DELETE FROM vote WHERE vote_id = ?";
        jdbcTemplate.update(sql, voteId);
    }

    public Vote findById(Long voteId) {
        String sql = "SELECT * FROM vote WHERE vote_id = ?";
        return jdbcTemplate.queryForObject(sql, new VoteMapper(), voteId);
    }

    public List<Vote> findByUserId(Long userId) {
        String sql = "SELECT * FROM vote WHERE user_id = ?";
        return jdbcTemplate.query(sql, new VoteMapper(), userId);
    }

    public List<Vote> findByResumeId(Long resumeId) {
        String sql = "SELECT * FROM vote WHERE resume_id = ?";
        return jdbcTemplate.query(sql, new VoteMapper(), resumeId);
    }

    private static class VoteMapper implements RowMapper<Vote> {
        @Override
        public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long voteId = rs.getLong("vote_id");
            int vote = rs.getInt("vote");
            Long userId = rs.getLong("user_id");
            Long resumeId = rs.getLong("resume_id");
            return new Vote(voteId, vote, userId, resumeId);
        }
    }

}
