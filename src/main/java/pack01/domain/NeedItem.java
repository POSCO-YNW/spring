package pack01.domain;

public class NeedItem {
    private Long resumeItemId;
    private String title;
    private Long postId;

    public NeedItem(Long resumeItemId, String title, Long postId) {
        this.resumeItemId = resumeItemId;
        this.title = title;
        this.postId = postId;
    }

    public Long getResumeItemId() {
        return resumeItemId;
    }

    public String getTitle() {
        return title;
    }

    public Long getPostId() {
        return postId;
    }
}