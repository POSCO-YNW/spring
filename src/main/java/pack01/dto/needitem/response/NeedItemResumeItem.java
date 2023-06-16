package pack01.dto.needitem.response;

public class NeedItemResumeItem {
    private Long needItemId;
    private String title;
    private Long postId;
    private Long resumeItemId;
    private String description;
    private Long resumeId;

    public NeedItemResumeItem(Long needItemId, String title, Long postId, Long resumeItemId, String description, Long resumeId) {
        this.needItemId = needItemId;
        this.title = title;
        this.postId = postId;
        this.resumeItemId = resumeItemId;
        this.description = description;
        this.resumeId = resumeId;
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

    public Long getResumeItemId() {
        return resumeItemId;
    }

    public String getDescription() {
        return description;
    }

    public Long getResumeId() {
        return resumeId;
    }
}
