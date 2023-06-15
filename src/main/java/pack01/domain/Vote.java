package pack01.domain;

public class Vote {
    private Long voteId;
    private int vote;
    private Long userId;
    private Long resumeId;

    public Vote(Long voteId, int vote, Long userId, Long resumeId) {
        this.voteId = voteId;
        this.vote = vote;
        this.userId = userId;
        this.resumeId = resumeId;
    }
    public Vote(int vote, Long userId, Long resumeId) {
        this.vote = vote;
        this.userId = userId;
        this.resumeId = resumeId;
    }
    public Long getVoteId() {
        return voteId;
    }

    public int getVote() {
        return vote;
    }


    public Long getUserId() {
        return userId;
    }

    public Long getResumeId() {
        return resumeId;
    }
}