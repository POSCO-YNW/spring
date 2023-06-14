package pack01.domain;

public class NeedItem {
    private Long resumeItem;
    private String title;
    private Long postId;

    public NeedItem(Long resumeItem, String title, Long postId) {
        this.resumeItem = resumeItem;
        this.title = title;
        this.postId = postId;
    }

    public Long getResumeItem() {
        return resumeItem;
    }

    public String getTitle() {
        return title;
    }

    public Long getPostId() {
        return postId;
    }
}