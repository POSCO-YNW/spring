package pack01.dto.post.response;

import java.util.List;

public class PostPagingResponse {
    private List<PostDepartmentResponse> results;
    private int totalCount;

    public PostPagingResponse(List<PostDepartmentResponse> results, int totalCount) {
        this.results = results;
        this.totalCount = totalCount;
    }

    public List<PostDepartmentResponse> getResults() {
        return results;
    }

    public int getTotalCount() {
        return totalCount;
    }
}