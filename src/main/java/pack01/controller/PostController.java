package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack01.domain.NeedItem;
import pack01.domain.Post;
import pack01.domain.User;
import pack01.dto.post.response.PostDepartmentResponse;
import pack01.service.NeedItemService;
import pack01.service.PostService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/postlist")
public class PostController {
    private final PostService postService;
    private final NeedItemService needItemService;

    @Autowired
    public PostController(PostService postService, NeedItemService needItemService) {
        this.postService = postService;
        this.needItemService = needItemService;
    }

    @GetMapping("/post")
    public String getPostById(Model model, @RequestParam(value = "id") Long postId) {
        model.addAttribute("post", postService.findById(postId));
        return "post/postDetailView";
    }
    @GetMapping("/post/edit")
    public String getPostEditInfo(Model model, @RequestParam(value = "id") Long postId) {
        model.addAttribute("post", postService.findById(postId));
        model.addAttribute("needItems", needItemService.findByPostId(postId));
        return "post/postWritingView";
    }

    @GetMapping("/deadline")
    public String updateEndDateSetDeadline(@RequestParam(value = "id") Long postId, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Post post = postService.findById(postId);
        if (Objects.equals(post.getAdminId(), loginUser.getUserId())) {
            postService.updateEndDateSetDeadline(postId);
        }
        return "redirect:/postlist/post?id=" + postId;
    }

    @GetMapping
    public String getList(Model model,
                          @RequestParam(value = "search", required = false) String search,
                          @RequestParam(value = "searchType", required = false) String searchType,
                          @RequestParam(value = "type", required = false) String type
    ) {

        if (search == null)
            search = "";
        if (searchType == null)
            searchType = "title";
        if (type == null)
            type = "latest";

        switch (type) {
            case "마감순":
                type = "deadline";
                break;
            case "최신순":
                type = "latest";
                break;
        }

        List<PostDepartmentResponse> posts = postService.findBySearchAndSearchTypeAndSort(search, searchType, type);

        model.addAttribute("posts", posts);
        model.addAttribute("search", search);

        return "post/postListView";
    }
//
//    // 검색
//    @GetMapping("/postlist/search")
//    public String getPostBySearch(Model model,
//                                  @RequestParam(value = "search") String search,
//                                  @RequestParam(value = "searchType") String value
//    ) {
//
//        return "search/searchResultView";
//    }
//
//    //정렬
//    @GetMapping("/postlist/sort")
//    public String getSortLatest(Model model, @RequestParam(value = "type") String type) {
//        List<Post> posts = postService.findAll();
//        System.out.println(type);
//        switch (type) {
//            case "latest":
//                Collections.sort(posts, Comparator.comparing(Post::getStartDate).reversed());
//                break;
//            case "deadline":
//                Collections.sort(posts, Comparator.comparing(Post::getEndDate));
//                break;
//        }
//        model.addAttribute("posts", posts);
//        return "post/postListView";
//    }

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
                             @RequestParam(value = "needItems[]", required = false) List<String> needItems,
                             HttpSession session
    ) {

        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        User loginUser = (User) session.getAttribute("loginUser");
        Post post = new Post(title, createdAt, createdAt, startDate, endDate, description, loginUser.getUserId(), loginUser.getDepartmentId());
        Long postId = postService.save(post);
        for (String item : needItems) {
            NeedItem needItem = new NeedItem(item, postId);
            needItemService.save(needItem);
        }
        return "redirect:/postlist";
    }

    @PostMapping("/post/edit")
    public String postEdit(Model model,
                             @RequestParam("title") String title,
                             @RequestParam("startDate") Date startDate,
                             @RequestParam("endDate") Date endDate,
                             @RequestParam("description") String description,
                             @RequestParam(value = "needItems[]", required = false) List<String> needItems,
                             HttpSession session
    ) {

        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        User loginUser = (User) session.getAttribute("loginUser");
        Post post = new Post(title, createdAt, createdAt, startDate, endDate, description, loginUser.getUserId(), loginUser.getDepartmentId());
        Long postId = postService.save(post);
        for (String item : needItems) {
            NeedItem needItem = new NeedItem(item, postId);
            needItemService.save(needItem);
        }
        return "redirect:/postlist";
    }
    @DeleteMapping("/post/delete")
    public String postDelete(Model model,
                             @RequestParam("title") String title,
                             @RequestParam("startDate") Date startDate,
                             @RequestParam("endDate") Date endDate,
                             @RequestParam("description") String description,
                             @RequestParam(value = "needItems[]", required = false) List<String> needItems,
                             HttpSession session
    ) {

        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        User loginUser = (User) session.getAttribute("loginUser");
        Post post = new Post(title, createdAt, createdAt, startDate, endDate, description, loginUser.getUserId(), loginUser.getDepartmentId());
        Long postId = postService.save(post);
        for (String item : needItems) {
            NeedItem needItem = new NeedItem(item, postId);
            needItemService.save(needItem);
        }
        return "redirect:/postlist";
    }
}
