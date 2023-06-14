package pack01.domain;

import pack01.domain.type.RoleType;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private Date birthday;
    private RoleType role;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long departmentId;

    public User() {
    }

    public User(Long userId, String username, String password, String email, Date birthday, RoleType role, String address, Timestamp createdAt, Timestamp updatedAt, Long departmentId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.departmentId = departmentId;
    }

    public User(String username, String password, String email, Date birthday, RoleType role, String address, Timestamp createdAt, Timestamp updatedAt, Long departmentId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.role = role;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.departmentId = departmentId;
    }

    public Long getUserId() {
        return userId;
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

    public Long getDepartmentId() {
        return departmentId;
    }
}
