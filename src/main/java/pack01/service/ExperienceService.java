package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Experience;
import pack01.repository.ExperienceRepository;

import java.util.List;

@Service
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    @Autowired
    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public Long save(Experience experience) {
        return experienceRepository.save(experience);
    }

    public Experience findById(Long experienceId) {
        return experienceRepository.findById(experienceId);
    }

    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    public void update(Experience experience) {
        experienceRepository.update(experience);
    }

    public void delete(Long experienceId) {
        experienceRepository.delete(experienceId);
    }

    public List<Experience> findByResumeId(Long resumeId) {
        return experienceRepository.findByResumeId(resumeId);
    }
}