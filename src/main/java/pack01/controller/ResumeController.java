package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack01.domain.User;
import pack01.service.ResumeService;
import pack01.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    private final UserService userService;
    private final ResumeService resumeService;

    @Autowired
    public ResumeController(UserService userService, ResumeService resumeService) {
        this.userService = userService;
        this.resumeService = resumeService;
    }

    ///resume?postId=2&departmentId=3
    @GetMapping("/post")
    public String getResume(@RequestParam("postId") int postId, @RequestParam("departmentId") int departmentId, Model model) {

    }

    @RequestMapping("/user")
    public String getUserInfo(Model model, HttpSession session) {

        Long userid = (Long) session.getAttribute("loginUser");

        User user = userService.findById(userid);
        model.addAttribute("userInfo", user);

        return "resumeView";
    }
}
