package pack01.domain;

public class NeedItem {
    private Long needItemId;
    private String title;
    private Long postId;

    public NeedItem(Long needItemId, String title, Long postId) {
        this.needItemId = needItemId;
        this.title = title;
        this.postId = postId;
    }

    public Long getNeedItemId() {
        return needItemId;
    }

    public String getTitle() {
        return title;
    }

    public Long getPostId() {
        return postId;
    }
}