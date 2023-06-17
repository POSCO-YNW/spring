package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack01.domain.Department;
import pack01.domain.NeedItem;
import pack01.domain.Post;
import pack01.domain.User;
import pack01.dto.post.response.PostDepartmentResponse;
import pack01.dto.post.response.PostPagingResponse;
import pack01.service.DepartmentService;
import pack01.service.NeedItemService;
import pack01.service.PostService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/postlist")
public class PostController {
    private final PostService postService;
    private final NeedItemService needItemService;
    private final DepartmentService departmentService;

    @Autowired
    public PostController(PostService postService, NeedItemService needItemService, DepartmentService departmentService) {
        this.postService = postService;
        this.needItemService = needItemService;
        this.departmentService = departmentService;
    }

    @GetMapping("/post")
    public String getPostById(Model model, @RequestParam(value = "id") Long postId) {
        System.out.println("controller: "+postId);
        model.addAttribute("post", postService.findByIdWithDepartment(postId));
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
                          @RequestParam(value = "type", required = false) String type,
                          @RequestParam(value = "page", required = false) Integer page
    ) {

        page = (page == null) ? 0 : page;

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

        PostPagingResponse pagingPosts = postService.findBySearchAndSearchTypeAndSort(search, searchType, type, page);

        model.addAttribute("postall", pagingPosts.getTotalCount());
        model.addAttribute("posts", pagingPosts.getResults());
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
                             @RequestParam(value = "description[]", required = false) List<String> descriptions,
                             @RequestParam(value = "needItems[]", required = false) List<String> needItems,
                             HttpSession session
    ) {

        Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
        User loginUser = (User) session.getAttribute("loginUser");
        String description = String.join("$$", descriptions);
        String title2 = title + " [" + descriptions.get(0) + "]";
        Post post = new Post(title2, createdAt, createdAt, startDate, endDate, description, loginUser.getUserId(), loginUser.getDepartmentId());
        Long postId = postService.save(post);
        if (needItems != null) {
            for (String item : needItems) {
                NeedItem needItem = new NeedItem(item, postId);
                needItemService.save(needItem);
            }
        }
        return "redirect:/postlist";
    }

    @PostMapping("/post/edit")
    public String postEdit(Model model,
                           @RequestParam("id") Long postId,
                           @RequestParam("title") String title,
                           @RequestParam("startDate") Date startDate,
                           @RequestParam("endDate") Date endDate,
                           @RequestParam(value = "description[]", required = false) List<String> descriptions,
                           @RequestParam(value = "needItems[]", required = false) List<String> needItems,
                           HttpSession session
    ) {

        Timestamp updateAt = Timestamp.valueOf(LocalDateTime.now());
        User loginUser = (User) session.getAttribute("loginUser");
        String description = String.join("$$", descriptions);

        String title2 = title;
        String descriptionType = descriptions.get(0);
        if (title.contains("경력")) {
            title2 = title.replace("[경력]", "");
            title2 += " [" + descriptionType + "]";
        } else if (title.contains("신입")) {
            title2 = title.replace("[신입]", "");
            title2 += " [" + descriptionType + "]";
        } else {
            title2 += " [" + descriptionType + "]";
        }


//        String title2 = title + " [" + descriptions.get(0) + "]";

        Post post = new Post(title2, updateAt, startDate, endDate, description, loginUser.getUserId(), loginUser.getDepartmentId());
        postService.update(post, postId);

        needItemService.deleteByPostId(postId);

        if (needItems != null) {
            for (String item : needItems) {
                NeedItem needItem = new NeedItem(item, postId);
                needItemService.save(needItem);
            }
        }
        return "redirect:/postlist";
    }

    @GetMapping("/post/delete")
    public String postDelete(Model model, @RequestParam("id") Long postId) {
        postService.delete(postId);
        return "redirect:/postlist";
    }

    @GetMapping("/kakaoMap")
    public String showMap(Model model) {
        List<Post> posts = postService.findAll();
        List<Department> departments = new ArrayList<>();
        List<PostDepartmentResponse> postDepartment = new ArrayList<>();

        for (Post p : posts) {
            postDepartment.add(postService.findByIdWithDepartment(p.getPostId()));
//            Long deptId = p.getDepartmentId();
//            departments.add(departmentService.findById(deptId));
        }
//        model.addAttribute("departments", departments);
        model.addAttribute("postDepartment", postDepartment);
        return "kakaoMap";
    }

}
