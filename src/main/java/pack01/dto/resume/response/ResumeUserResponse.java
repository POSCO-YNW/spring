package pack01.dto.resume.response;

import pack01.domain.type.ResumeStatusType;
import pack01.domain.type.RoleType;

import java.sql.Date;
import java.sql.Timestamp;

public class ResumeUserResponse {
    private Long resumeId;
    private Long applicantId;
    private Long postId;
    private Long departmentId;
    private ResumeStatusType status;
    private String description;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private RoleType role;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ResumeUserResponse(Long resumeId, Long applicantId, Long postId, Long departmentId, ResumeStatusType status, String description, String username, String password, String email, String phoneNumber, Date birthday, RoleType role, String address, Timestamp createdAt, Timestamp updatedAt) {
        this.resumeId = resumeId;
        this.applicantId = applicantId;
        this.postId = postId;
        this.departmentId = departmentId;
        this.status = status;
        this.description = description;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.role = role;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public RoleType getRole() {
        return role;
    }

    public String getAddress() {
        return address;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
