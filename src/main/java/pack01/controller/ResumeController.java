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
            @RequestParam(value = "certification[]", required = false) List<String> certifications,
            @RequestParam(value = "cf_level[]", required = false) List<String> certificationLevels,
            @RequestParam(value = "cf_date[]", required = false) List<String> certificationDates,
            @RequestParam(value = "skill_stack[]", required = false) List<String> skillStacks,
            @RequestParam(value = "skill_level[]", required = false) List<LevelType> skillLevels,
            @RequestParam(value = "ex_company[]", required = false) List<String> experienceCompanies,
            @RequestParam(value = "ex_period[]", required = false) List<Integer> experiencePeriods,
            @RequestParam(value = "ex_work[]", required = false) List<String> experienceWorks,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            HttpSession session,
            Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

//        resumeService.saveInfo(loginUser.getUserId(), postId, departmentId, );

        return "resume_result";
    }
}
