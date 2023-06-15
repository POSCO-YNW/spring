package pack01.domain;

public class Resume {

    private Long resumeId;
    private Long applicantId;
    private Long postId;
    private Long departmentId;
    private String status;
    private String description;

    Resume() {

    }

    public Resume(Long resumeId, Long applicantId, Long postId, Long departmentId, String status, String description) {
        this.resumeId = resumeId;
        this.applicantId = applicantId;
        this.postId = postId;
        this.departmentId = departmentId;
        this.status = status;
        this.description = description;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public Long getPostId() {
        return postId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
