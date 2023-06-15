package pack01.domain.type;

public enum ResumeStatusType {
    PASS("합격"), FAIL("탈락"), UNREAD("미열람"), READ("열람");

    private final String description;

    ResumeStatusType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
