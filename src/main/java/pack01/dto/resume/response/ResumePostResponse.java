package pack01.dto.resume.response;

import pack01.domain.type.ResumeStatusType;

import java.sql.Date;
import java.sql.Timestamp;

public class ResumePostResponse {
    private Long resumeId;
    private Long applicantId;
    private Long postId;
    private Long departmentId;
    private ResumeStatusType status;
    private String description;
    private String title;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Date startDate;
    private Date endDate;
    private String postDescription;
    private Long adminId;

    public ResumePostResponse(Long resumeId, Long applicantId, Long postId, Long departmentId, ResumeStatusType status, String description, String title, Timestamp createdAt, Timestamp updatedAt, Date startDate, Date endDate, String postDescription, Long adminId) {
        this.resumeId = resumeId;
        this.applicantId = applicantId;
        this.postId = postId;
        this.departmentId = departmentId;
        this.status = status;
        this.description = description;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.postDescription = postDescription;
        this.adminId = adminId;
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

    public ResumeStatusType getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPostDescription() {
        return postDescription;
    }

    public Long getAdminId() {
        return adminId;
    }
}
