package pack01.controller.form;

import pack01.domain.Certification;
import pack01.domain.Experience;
import pack01.domain.Skill;

import java.util.List;

public class ResumeForm {
    private List<Certification> certifications;
    private List<Skill> skills;
    private List<Experience> experiences;
    private String description;

    public ResumeForm(List<Certification> certifications, List<Skill> skills, List<Experience> experiences, String description) {
        this.certifications = certifications;
        this.skills = skills;
        this.experiences = experiences;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<Skill> getSkills() {
        return skills;
    }


    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }
}