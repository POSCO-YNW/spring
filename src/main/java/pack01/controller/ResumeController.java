package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack01.controller.form.ResumeForm;
import pack01.domain.*;
import pack01.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final PostService postService;
    private final DepartmentService departmentService;

    @Autowired
    public ResumeController(UserService userService, ResumeService resumeService, PostService postService, DepartmentService departmentService) {
        this.userService = userService;
        this.resumeService = resumeService;
        this.postService = postService;
        this.departmentService = departmentService;
    }

    ///resume/post?postId=1&departmentId=2
    @GetMapping("/post")
    public String getResume(@RequestParam("postId") Long postId, @RequestParam("departmentId") Long departmentId, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        Department department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("userInfo", loginUser);
        return "resume/resumeView";
    }

    @PostMapping("/post")
    public String submitResume(@RequestParam("postId") Long postId, @RequestParam("departmentId") Long departmentId, @RequestParam("description") String description, @ModelAttribute("resumeForm") ResumeForm resumeForm, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");
        resumeService.saveInfo(userId, postId, departmentId, description, resumeForm);

        return "redirect:/resume/success";
    }
}
