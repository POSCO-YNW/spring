package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pack01.domain.Post;
import pack01.domain.User;
import pack01.repository.DepartmentRepository;
import pack01.repository.PostRepository;
import pack01.repository.UserRepository;
import pack01.service.DepartmentService;
import pack01.service.PostService;
import pack01.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    private final PostRepository postRepository;

    @Autowired
    public MypageController(PostRepository postRepository) {
        this.postRepository = postRepository;

    }

    @GetMapping("/get")
    public String myPage(HttpSession session, Model model){
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("마이페이지");
        PostService postService = new PostService(postRepository);
        List<Post> postList = postService.findBymyResume(loginUser.getUserId());
        model.addAttribute("myresume", postList);

        System.out.println("마이페이지끝");
        return "mypage/mypageView";
    }
}
