package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Resume;
import pack01.repository.ResumeRepository;

import java.util.List;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume findById(Long id) {
        return resumeRepository.findById(id);
    }

    public List<Resume> findAll() {
        return resumeRepository.findAll();
    }

    public void update(Resume resume) {
        resumeRepository.update(resume);
    }

    public void delete(Long id) {
        resumeRepository.delete(id);
    }

}
