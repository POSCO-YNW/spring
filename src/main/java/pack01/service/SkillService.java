package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Skill;
import pack01.repository.SkillRepository;

import java.util.List;

@Service
public class SkillService {
    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Long save(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill findById(Long skillId) {
        return skillRepository.findById(skillId);
    }

    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    public void update(Skill skill) {
        skillRepository.update(skill);
    }

    public void delete(Long skillId) {
        skillRepository.delete(skillId);
    }
}