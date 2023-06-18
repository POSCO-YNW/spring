package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Post;
import pack01.dto.post.response.PostDepartmentResponse;
import pack01.dto.post.response.PostPagingResponse;
import pack01.repository.PostRepository;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long save(Post post) {
        return postRepository.save(post);
    }

    public void update(Post post, Long postId) {
        postRepository.update(post, postId);
    }

    public void delete(Long postId) {
        postRepository.delete(postId);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> findByTitle(String postTitle) {
        return postRepository.findByTitle(postTitle);
    }

    public PostDepartmentResponse findByIdWithDepartment(Long postId) {
        return postRepository.findByIdWithDepartment(postId);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findByMyResume(Long id) {
        return postRepository.findByMyResume(id);
    }


    public void updateEndDateSetDeadline(Long postId) {
        postRepository.updateEndDateSetDeadline(postId);
    }

    public PostPagingResponse findBySearchAndSearchTypeAndSort(String search, String searchType, String type, Integer page, HttpSession session) {
        List<PostDepartmentResponse> posts = new ArrayList<>();
        int totalCount = 0;

        switch (searchType) {
            case "title":
                PostPagingResponse titleResult = postRepository.findPostAndDepartmentByPostTitle(search, page);
                posts = titleResult.getResults();
                totalCount = titleResult.getTotalCount();
                break;
            case "department":
                PostPagingResponse departmentResult = postRepository.findPostAndDepartmentByDepartmentTitle(search, page);
                posts = departmentResult.getResults();
                totalCount = departmentResult.getTotalCount();
                break;
            default:
                // Handle invalid searchType if needed
                return new PostPagingResponse(Collections.emptyList(), 0);
        }

        switch (type) {
            case "latest":
                posts.sort(Comparator.comparing(PostDepartmentResponse::getStartDate).reversed());
                break;
            case "deadline":
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

                posts.sort((p1, p2) -> {
                    Date endDate1 = p1.getEndDate();
                    Date endDate2 = p2.getEndDate();

                    if (endDate1.after(currentTimestamp) && endDate2.before(currentTimestamp)) {
                        return -1;
                    } else if (endDate1.before(currentTimestamp) && endDate2.after(currentTimestamp)) {
                        return 1;
                    } else {
                        return endDate1.compareTo(endDate2);
                    }
                });
                break;
            default:
                // Handle invalid type if needed
                break;
        }

        return new PostPagingResponse(posts, totalCount);
    }
}
