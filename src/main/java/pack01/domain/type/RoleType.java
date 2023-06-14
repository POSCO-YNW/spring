package pack01.domain.type;

public enum RoleType {
    APPLICANT("지원자"), EMPLOYEE("직원"), ADMIN("담당자");

    private String description;

    RoleType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
