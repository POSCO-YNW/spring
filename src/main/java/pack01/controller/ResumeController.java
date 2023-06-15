package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack01.domain.*;
import pack01.domain.type.LevelType;
import pack01.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    private final PostService postService;
    private final DepartmentService departmentService;
    private final NeedItemService needItemService;
    private final ResumeService resumeService;

    @Autowired
    public ResumeController(PostService postService, DepartmentService departmentService, NeedItemService needItemService, ResumeService resumeService) {
        this.postService = postService;
        this.departmentService = departmentService;
        this.needItemService = needItemService;
        this.resumeService = resumeService;
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
        model.addAttribute("needItems", needItemService.findByPostId(postId));
        return "resume/resumeView";
    }

    @PostMapping("/post")
    public String submitResume(
            @RequestParam("postId") Long postId, @RequestParam("departmentId") Long departmentId,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("birthday") String birthday,
            @RequestParam("address") String address,
            @RequestParam("certification[]") List<String> certifications,
            @RequestParam("cf_level[]") List<String> certificationLevels,
            @RequestParam("cf_date[]") List<String> certificationDates,
            @RequestParam("skill_stack[]") List<String> skillStacks,
            @RequestParam("skill_level[]") List<LevelType> skillLevels,
            @RequestParam("ex_company[]") List<String> experienceCompanies,
            @RequestParam("ex_period[]") List<Integer> experiencePeriods,
            @RequestParam("ex_work[]") List<String> experienceWorks,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            HttpSession session,
            Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

//        resumeService.saveInfo(loginUser.getUserId(), postId, departmentId, );

        return "resume_result";
    }
}
