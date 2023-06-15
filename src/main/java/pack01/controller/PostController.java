package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack01.domain.Post;
import pack01.service.EducationService;
import pack01.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/postlist")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getList(Model model){
        model.addAttribute("posts", postService.findAll());
        return "post/postListView";
    }
    @GetMapping("/post")
    public String getPost(Model model, @RequestParam(value = "id") Long postId){
        model.addAttribute("post", postService.findById(postId));
        return "post/postDetailView";
    }
}
