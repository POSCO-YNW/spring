package pack01.dto.resume.response;

import pack01.domain.Vote;
import pack01.domain.type.ResumeStatusType;

import java.util.List;

public class ResumeVoteResponse {
    private Long resumeId;
    private List<Vote> votes;

    public ResumeVoteResponse(Long resumeId, List<Vote> votes) {
        this.resumeId = resumeId;
        this.votes = votes;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public List<Vote> getVotes() {
        return votes;
    }
}
