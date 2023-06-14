package pack01.domain;

public class ResumeItem {
    private Long resumeItemId;
    private String desciption;
    private Long needItemId;
    private Long resumeId;

    public ResumeItem() {
    }

    public ResumeItem(Long resumeItemId, String desciption, Long needItemId, Long resumeId) {
        this.resumeItemId = resumeItemId;
        this.desciption = desciption;
        this.needItemId = needItemId;
        this.resumeId = resumeId;
    }

    public Long getResumeItemId() {
        return resumeItemId;
    }

    public String getDesciption() {
        return desciption;
    }

    public Long getNeedItemId() {
        return needItemId;
    }

    public Long getResumeId() {
        return resumeId;
    }
}
