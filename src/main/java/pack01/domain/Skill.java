package pack01.domain;

public class Skill {
    private Long skillId;
    private String stack;
    private String level;
    private Long resumeId;

    public Skill() {
    }

    public Skill(Long skillId, String stack, String level, Long resumeId) {
        this.skillId = skillId;
        this.stack = stack;
        this.level = level;
        this.resumeId = resumeId;
    }

    public Skill(String stack, String level, Long resumeId) {
        this.stack = stack;
        this.level = level;
        this.resumeId = resumeId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public String getStack() {
        return stack;
    }

    public String getLevel() {
        return level;
    }

    public Long getResumeId() {
        return resumeId;
    }
}
