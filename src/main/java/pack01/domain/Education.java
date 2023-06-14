package pack01.domain;

import pack01.domain.type.EducationType;

public class Education {
    private Long educationId;
    private EducationType type;
    private String school;
    private int grade;
    private Long resumeId;

    public Education() {
    }

    public Education(Long educationId, EducationType type, String school, int grade, Long resumeId) {
        this.educationId = educationId;
        this.type = type;
        this.school = school;
        this.grade = grade;
        this.resumeId = resumeId;
    }

    public Education(EducationType type, String school, int grade, Long resumeId) {
        this.type = type;
        this.school = school;
        this.grade = grade;
        this.resumeId = resumeId;
    }

    public Long getEducationId() {
        return educationId;
    }

    public EducationType getType() {
        return type;
    }

    public String getSchool() {
        return school;
    }

    public int getGrade() {
        return grade;
    }

    public Long getResumeId() {
        return resumeId;
    }
}
