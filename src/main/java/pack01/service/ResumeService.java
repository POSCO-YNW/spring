package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.controller.form.ResumeForm;
import pack01.domain.Certification;
import pack01.domain.Experience;
import pack01.domain.Resume;
import pack01.domain.Skill;
import pack01.domain.type.ResumeStatusType;
import pack01.repository.*;

import java.util.List;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final CertificationRepository certificationRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillRepository skillRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, CertificationRepository certificationRepository, EducationRepository educationRepository, ExperienceRepository experienceRepository, SkillRepository skillRepository) {
        this.resumeRepository = resumeRepository;
        this.certificationRepository = certificationRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.skillRepository = skillRepository;
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

    public void saveInfo(Long userId, Long postId, Long departmentId, String description, ResumeForm resumeForm) {
        List<Certification> certifications = resumeForm.getCertifications();
        List<Skill> skills = resumeForm.getSkills();
        List<Experience> experiences = resumeForm.getExperiences();

        Long resumeId = resumeRepository.save(new Resume(userId, postId, departmentId, ResumeStatusType.UNREAD, description));

        for (Certification certification : certifications) {
            certification.setResumeId(resumeId);
            certificationRepository.save(certification);
        }
        for (Skill skill : skills) {
            skill.setResumeId(resumeId);
            skillRepository.save(skill);
        }
        for (Experience experience : experiences) {
            experience.setResumeId(resumeId);
            experienceRepository.save(experience);
        }
    }
}
