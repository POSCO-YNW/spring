package pack01.domain;

import pack01.domain.type.LevelType;

public class Skill {
    private Long skillId;
    private String stack;
    private LevelType level;
    private Long resumeId;

    public Skill() {
    }

    public Skill(Long skillId, String stack, LevelType level, Long resumeId) {
        this.skillId = skillId;
        this.stack = stack;
        this.level = level;
        this.resumeId = resumeId;
    }

    public Skill(String stack, LevelType level, Long resumeId) {
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

    public LevelType getLevel() {
        return level;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
}
