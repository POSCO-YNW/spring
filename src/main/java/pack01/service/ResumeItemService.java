package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.ResumeItem;
import pack01.repository.ResumeItemRepository;

import java.util.List;

@Service
public class ResumeItemService {

    private ResumeItemRepository resumeItemRepository;

    @Autowired
    public ResumeItemService(ResumeItemRepository resumeItemRepository) {
        this.resumeItemRepository = resumeItemRepository;
    }

    public Long save(ResumeItem resumeItem) {
        return resumeItemRepository.save(resumeItem);
    }

    public ResumeItem findById(Long resumeItemId) {
        return resumeItemRepository.findById((resumeItemId));
    }

    public List<ResumeItem> findAll() {
        return resumeItemRepository.findAll();
    }

    public void update(ResumeItem resumeItem) {
        resumeItemRepository.update(resumeItem);
    }

    public void delete(Long resumeItemId) {
        resumeItemRepository.delete(resumeItemId);
    }
}
