package pack01.domain;

public class Department {
    private Long departmentId;
    private String name;
    private String telephoneNumber;
    private String deptKey;
    private String location;
    private double x;
    private double y;

    public Department() {
    }

    public Department(Long departmentId, String name, String telephoneNumber, String deptKey, String location, double x, double y) {
        this.departmentId = departmentId;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.deptKey = deptKey;
        this.location = location;
        this.x = x;
        this.y = y;
    }
    public Department(String name, String telephoneNumber, String deptKey, String location, double x, double y) {
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.deptKey = deptKey;
        this.location = location;
        this.x = x;
        this.y = y;
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