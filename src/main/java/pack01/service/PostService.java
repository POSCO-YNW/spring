package pack01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack01.domain.Post;
import pack01.repository.PostRepository;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) { this.postRepository = postRepository; }

    public Long save(Post post) { return postRepository.save(post); }
    public void update(Post post) { postRepository.update(post); }
    public void delete(Long postId) { postRepository.delete(postId); }
    public Post findById(Long postId) { return postRepository.findById(postId); }
    public List<Post> findByTitle(String postTitle) { return postRepository.findByTitle(postTitle); }
    public List<Post> findByDepartmentName(String departmentName) { return postRepository.findByDepartmentName(departmentName); }

    public List<Post> findAll() { return postRepository.findAll(); }


}
