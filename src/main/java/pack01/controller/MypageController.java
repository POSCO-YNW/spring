package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack01.domain.Post;
import pack01.domain.User;
import pack01.repository.PostRepository;
import pack01.repository.UserRepository;
import pack01.service.DepartmentService;
import pack01.service.PostService;
import pack01.service.UserService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DepartmentService departmentService;


    @Autowired
    public MypageController(PostRepository postRepository, UserRepository userRepository, DepartmentService departmentService) {
        this.postRepository = postRepository;

        this.userRepository = userRepository;
        this.departmentService = departmentService;
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

    @GetMapping("/edituser")
    public String editUser(HttpSession session, Model model){
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("개인정보수정");

        UserService userService = new UserService(userRepository,departmentService);
        User user = userService.findById(loginUser.getUserId());

        model.addAttribute("userlist", user);

        System.out.println("개인정보수정끝");
        return "mypage/edituserView";
    }

    @PostMapping("/edituser")
    public String editProcess(
            @RequestParam("name") String name,
            @RequestParam("pw") String password,
            @RequestParam("email") String email,
            @RequestParam("phone") String phoneNumber,
            @RequestParam("birthday") Date birthday,
            @RequestParam("address") String address,
            HttpSession session, Model model
            ) {





        return "mypage/edituserView";
    }
}
