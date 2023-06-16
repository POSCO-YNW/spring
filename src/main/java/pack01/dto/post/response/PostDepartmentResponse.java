package pack01.dto.post.response;

import java.sql.Date;
import java.sql.Timestamp;

public class PostDepartmentResponse {
    private Long postId;
    private String title;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Date startDate;
    private Date endDate;
    private String description;
    private Long adminId;
    private Long departmentId;
    private String name;
    private String telephoneNumber;
    private String deptKey;
    private String location;
    private double x;
    private double y;

    public PostDepartmentResponse(Long postId, String title, Timestamp createdAt, Timestamp updatedAt, Date startDate, Date endDate, String description, Long adminId, Long departmentId, String name, String telephoneNumber, String deptKey, String location, double x, double y) {
        this.postId = postId;
        this.title = title;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.adminId = adminId;
        this.departmentId = departmentId;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.deptKey = deptKey;
        this.location = location;
        this.x = x;
        this.y = y;
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

    public String getName() {
        return name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getDeptKey() {
        return deptKey;
    }

    public String getLocation() {
        return location;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
