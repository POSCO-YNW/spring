package pack01.domain;

public class Experience {
    private Long experienceId;
    private String company;
    private int period;
    private String work;
    private Long resumeId;

    public Experience() {
    }

    public Experience(Long experienceId, String company, int period, String work, Long resumeId) {
        this.experienceId = experienceId;
        this.company = company;
        this.period = period;
        this.work = work;
        this.resumeId = resumeId;
    }

    public Experience(String company, int period, String work, Long resumeId) {
        this.company = company;
        this.period = period;
        this.work = work;
        this.resumeId = resumeId;
    }

    public Long getExperienceId() {
        return experienceId;
    }

    public String getCompany() {
        return company;
    }

    public int getPeriod() {
        return period;
    }

    public String getWork() {
        return work;
    }

    public Long getResumeId() {
        return resumeId;
    }
}
