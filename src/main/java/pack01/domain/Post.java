package pack01.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Post {
    private Long postId;
    private String title;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Date startDate;
    private Date endDate;
    private String description;
    private Long adminId;
    private Long departmentId;


    public Post(Long postId, String title, Timestamp createdAt, Timestamp updatedAt, Date startDate, Date endDate, String description, Long adminId, Long departmentId) {
        this.postId = postId;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.adminId = adminId;
        this.departmentId = departmentId;
    }
    public Post(String title, Timestamp createdAt, Timestamp updatedAt, Date startDate, Date endDate, String description, Long adminId, Long departmentId) {
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.adminId = adminId;
        this.departmentId = departmentId;
    }
    public Long getPostId() {
        return postId;
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

    public String getDescription() {
        return description;
    }

    public Long getAdminId() {
        return adminId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }
}