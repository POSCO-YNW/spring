package pack01.domain.type;

public enum LevelType {
    HIGH("상"), MIDDLE("중"), LOW("하");

    private final String description;

    LevelType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
