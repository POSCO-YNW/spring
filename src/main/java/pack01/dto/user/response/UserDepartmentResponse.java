package pack01.dto.user.response;

import pack01.domain.type.RoleType;

import java.sql.Date;
import java.sql.Timestamp;

public class UserDepartmentResponse {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private RoleType role;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Long departmentId;
    private String name;
    private String telephoneNumber;
    private String deptKey;
    private String location;
    private double x;
    private double y;

    public UserDepartmentResponse(Long userId, String username, String password, String email, String phoneNumber, Date birthday, RoleType role, String address, Timestamp createdAt, Timestamp updatedAt, Long departmentId, String name, String telephoneNumber, String deptKey, String location, double x, double y) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.role = role;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.departmentId = departmentId;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.deptKey = deptKey;
        this.location = location;
        this.x = x;
        this.y = y;
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
