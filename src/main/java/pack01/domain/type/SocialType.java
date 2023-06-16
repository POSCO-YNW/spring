package pack01.domain.type;

public enum SocialType {
    GITHUB("깃허브"), TISTORY("티스토리"), BOJ("백준");

    private final String description;

    SocialType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
