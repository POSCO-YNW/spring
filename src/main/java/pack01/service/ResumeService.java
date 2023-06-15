package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.controller.form.ResumeForm;
import pack01.domain.*;
import pack01.domain.type.LevelType;
import pack01.domain.type.ResumeStatusType;
import pack01.repository.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final CertificationRepository certificationRepository;
    private final EducationRepository educationRepository;
    private final ExperienceRepository experienceRepository;
    private final SkillRepository skillRepository;
    private final ResumeItemRepository resumeItemRepository;

    @Autowired
    public ResumeService(ResumeRepository resumeRepository, CertificationRepository certificationRepository, EducationRepository educationRepository, ExperienceRepository experienceRepository, SkillRepository skillRepository, ResumeItemRepository resumeItemRepository) {
        this.resumeRepository = resumeRepository;
        this.certificationRepository = certificationRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
        this.skillRepository = skillRepository;
        this.resumeItemRepository = resumeItemRepository;
    }

    public Long save(Resume resume) {
        return resumeRepository.save(resume);
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

    public void saveResumeForm(Long resumeId, ResumeForm resumeForm) {
        List<Certification> certifications = resumeForm.getCertifications();
        List<Skill> skills = resumeForm.getSkills();
        List<Experience> experiences = resumeForm.getExperiences();
        List<Long> questions = resumeForm.getQuestionId();
        List<String> answers = resumeForm.getAnswers();

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
        for (int i = 0; i < questions.size(); i++) {
            resumeItemRepository.save(new ResumeItem(answers.get(i), questions.get(i), resumeId));
        }
    }

    public ResumeForm makeResumeForm(Long resumeId, List<String> certifications, List<String> certificationLevels, List<String> certificationDates,
                                     List<String> skillStacks, List<LevelType> skillLevels,
                                     List<String> experienceCompanies, List<Integer> experiencePeriods, List<String> experienceWorks,
                                     List<Long> questions, List<String> answers) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<Certification> certificationList = new ArrayList<>();
        List<Skill> skillList = new ArrayList<>();
        List<Experience> experienceList = new ArrayList<>();

        for (int i = 0; i < certifications.size(); i++) {
            java.util.Date parsedDate = null;
            try {
                parsedDate = dateFormat.parse(certificationDates.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Date sqlDate = new Date(Objects.requireNonNull(parsedDate).getTime());
            certificationList.add(new Certification(certifications.get(i), certificationLevels.get(i), sqlDate, resumeId));
        }

        for (int i = 0; i < skillStacks.size(); i++) {
            skillList.add(new Skill(skillStacks.get(i), skillLevels.get(i), resumeId));
        }

        for (int i = 0; i < experiencePeriods.size(); i++) {
            experienceList.add(new Experience(experienceCompanies.get(i), experiencePeriods.get(i), experienceWorks.get(i), resumeId));
        }


        return new ResumeForm(certificationList, skillList, experienceList, questions, answers);
    }
}
