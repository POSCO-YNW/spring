package pack01.domain;

public class ResumeItem {
    private Long resumeItemId;
    private String description;
    private Long needItemId;
    private Long resumeId;

    public ResumeItem() {
    }

    public ResumeItem(Long resumeItemId, String description, Long needItemId, Long resumeId) {
        this.resumeItemId = resumeItemId;
        this.description = description;
        this.needItemId = needItemId;
        this.resumeId = resumeId;
    }

    public Long getResumeItemId() {
        return resumeItemId;
    }

    public String getDescription() {
        return description;
    }

    public Long getNeedItemId() {
        return needItemId;
    }

    public Long getResumeId() {
        return resumeId;
    }
}
