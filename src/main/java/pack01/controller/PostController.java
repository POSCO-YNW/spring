package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack01.domain.Post;
import pack01.service.EducationService;
import pack01.service.PostService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
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
//    @GetMapping("/postlist/search") // 셀렉박스에서
//    public String getPostByDepartmentId(Model model, @RequestParam(value = "id") Long departmentId){
//        model.addAttribute("department", postService.findByTitle(title));
//        return "search/searchResultView";
//    }
}
