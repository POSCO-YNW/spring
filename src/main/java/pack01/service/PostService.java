package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Post;
import pack01.dto.post.response.PostDepartmentResponse;
import pack01.repository.PostRepository;

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

    public void update(Post post) {
        postRepository.update(post);
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

    public List<Post> findByDepartmentName(String departmentName) {
        return postRepository.findByDepartmentName(departmentName);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }


    public List<PostDepartmentResponse> findBySearchAndSearchTypeAndSort(String search, String searchType, String type) {
        List<PostDepartmentResponse> posts = new ArrayList<>();

        switch (searchType) {
            case "title":
                posts = postRepository.findPostAndDepartmentByPostTitle(search);
                break;
            case "department":
                posts = postRepository.findPostAndDepartmentByDepartmentTitle(search);
                break;
        }

        switch (type) {
            case "latest":
                posts.sort(Comparator.comparing(PostDepartmentResponse::getStartDate).reversed());
                break;
            case "deadline":
                posts.sort(Comparator.comparing(PostDepartmentResponse::getEndDate));
                break;
        }

        return posts;
    }
}
