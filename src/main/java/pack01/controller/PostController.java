package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pack01.domain.Post;
import pack01.domain.User;
import pack01.domain.type.LevelType;
import pack01.service.EducationService;
import pack01.service.PostService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    @PostMapping("/")


    @GetMapping("/postlist")
    public String getList(Model model){
        model.addAttribute("posts", postService.findAll());
        return "post/postListView";
    }
    @GetMapping("/postlist/post")
    public String getPostById(Model model, @RequestParam(value = "id") Long postId){
        model.addAttribute("post", postService.findById(postId));
        return "post/postDetailView";
    }

    //정렬
    @GetMapping("/postlist/sort")
    public String getSortLatest(Model model, @RequestParam(value = "type") String type){
        List<Post> posts = postService.findAll();
        System.out.println(type);
        switch (type){
            case "latest":
                Collections.sort(posts, Comparator.comparing(Post::getStartDate).reversed());
                break;
            case "deadline":
                Collections.sort(posts, Comparator.comparing(Post::getEndDate));
                break;
        }
        model.addAttribute("posts", posts);
        return "post/postListView";
    }

    // 검색
    @GetMapping("/postlist/search")
    public String getPostBySearch(Model model,
                                  @RequestParam(value = "search") String search,
                                  @RequestParam(value = "searchType") String value
    ){
        switch (value){
            case "title":
                model.addAttribute("posts", postService.findByTitle(search));
                break;
            case "department":
                model.addAttribute("posts", postService.findByDepartmentName(search));
        }
        return "search/searchResultView";
    }

    // 글 작성
    @GetMapping("/post/write")
    public String showPostWriteForm(Model model) {
        return "post/postWritingView";
    }

    @PostMapping("/post/create")
    public String postCreate(Model model,
                             @RequestParam("title") String title,
                             @RequestParam("startDate") Date startDate,
                             @RequestParam("endDate") Date endDate,
                             @RequestParam("description") String description,
                             HttpSession session
                             ){

        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        Long userId = (Long) session.getAttribute("user_id");
        Long departmentId = (Long) session.getAttribute("department_id");
        Post post = new Post(title, createdAt, null, startDate, endDate, description, userId, departmentId);
        postService.save(post);
        return "redirect:/post/writeSuccessView";
    }

}
