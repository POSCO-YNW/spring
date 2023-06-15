package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Education;
import pack01.repository.EducationRepository;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;

    @Autowired
    public EducationService(EducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    public Long save(Education education) {
        return educationRepository.save(education);
    }

    public List<Education> findAll() {
        return educationRepository.findAll();
    }
}
