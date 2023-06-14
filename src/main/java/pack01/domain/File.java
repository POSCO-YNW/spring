package pack01.domain;

public class File {
    private Long fileId;
    private String link;
    private Long postId;

    public File(Long fileId, String link, Long postId) {
        this.fileId = fileId;
        this.link = link;
        this.postId = postId;
    }
    public File(String link, Long postId) {
        this.link = link;
        this.postId = postId;
    }
    public Long getFileId() {
        return fileId;
    }

    public String getLink() {
        return link;
    }

    public Long getPostId() {
        return postId;
    }
}