package pack01.domain.type;

public enum EducationType {
    HIGHSCHOOL("고등학교"), UNIVERSITY("대학교"), GRADUATESCHOOL("대학원");

    private String description;

    EducationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
