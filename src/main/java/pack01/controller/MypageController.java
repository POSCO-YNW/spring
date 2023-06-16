package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack01.domain.SocialAccount;
import pack01.domain.User;
import pack01.domain.type.SocialType;
import pack01.dto.resume.response.ResumePostResponse;
import pack01.repository.PostRepository;
import pack01.repository.UserRepository;
import pack01.service.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final DepartmentService departmentService;
    private final SocialAccountService socialAccountService;
    private final ResumeService resumeService;


    @Autowired
    public MypageController(PostRepository postRepository, UserRepository userRepository, DepartmentService departmentService, SocialAccountService socialAccountService, ResumeService resumeService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.departmentService = departmentService;
        this.socialAccountService = socialAccountService;
        this.resumeService = resumeService;
    }

    @GetMapping("/get")
    public String myPage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("마이페이지");
        List<ResumePostResponse> ResumePostResponses = resumeService.findResumePostResponseByApplicantId(loginUser.getUserId());
        model.addAttribute("ResumePostResponses", ResumePostResponses);

        System.out.println("마이페이지끝");
        return "mypage/mypageView";
    }

    @GetMapping("/edituser")
    public String editUser(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("개인정보수정");

        UserService userService = new UserService(userRepository, departmentService);

        List<SocialAccount> socialAccounts = socialAccountService.findByUserId(loginUser.getUserId());
        User user = userService.findById(loginUser.getUserId());

        model.addAttribute("userlist", user);
        model.addAttribute("socialAccounts", socialAccounts);

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
            @RequestParam(value = "github", required = false) String github,
            @RequestParam(value = "tistory", required = false) String tistory,
            @RequestParam(value = "boj", required = false) String boj,
            HttpSession session, Model model
    ) {

        UserService userService = new UserService(userRepository, departmentService);

        User loginUser = (User) session.getAttribute("loginUser");

        User user = new User(loginUser.getUserId(), name, password, email, phoneNumber,
                birthday, loginUser.getRole(), address, loginUser.getCreatedAt(),
                loginUser.getUpdatedAt(), loginUser.getDepartmentId());

        userService.update(user);

        socialAccountService.deleteByUserId(loginUser.getUserId());

        if (github != null)
            socialAccountService.save(new SocialAccount(SocialType.GITHUB, github, null, loginUser.getUserId()));
        if (tistory != null)
            socialAccountService.save(new SocialAccount(SocialType.TISTORY, tistory, null, loginUser.getUserId()));
        if (boj != null)
            socialAccountService.save(new SocialAccount(SocialType.BOJ, boj, null, loginUser.getUserId()));

        return "redirect:/mypage/get";
    }
}
