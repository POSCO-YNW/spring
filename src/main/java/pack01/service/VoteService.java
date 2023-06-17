package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Vote;
import pack01.repository.VoteRepository;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void save(Vote vote) {
        voteRepository.save(vote);
    }

    public void update(Vote vote) {
        voteRepository.update(vote);
    }

    public void delete(Long voteId) {
        voteRepository.delete(voteId);
    }

    public Vote findById(Long voteId) {
        return voteRepository.findById(voteId);
    }

    public List<Vote> findByUserId(Long userId) {
        return voteRepository.findByUserId(userId);
    }

    public List<Vote> findByResumeId(Long resumeId) {
        return voteRepository.findByResumeId(resumeId);
    }

    public void deleteByUserId(Long userId) {
        voteRepository.deleteByUserId(userId);
    }
}
