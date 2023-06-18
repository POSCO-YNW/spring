package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack01.controller.form.ResumeForm;
import pack01.domain.*;
import pack01.domain.type.LevelType;
import pack01.domain.type.ResumeStatusType;
import pack01.domain.type.RoleType;
import pack01.dto.resume.response.ResumeUserResponse;
import pack01.dto.resume.response.ResumeVoteResponse;
import pack01.service.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/resume")
public class ResumeController {

    private final PostService postService;
    private final DepartmentService departmentService;
    private final NeedItemService needItemService;
    private final ResumeService resumeService;
    private final SocialAccountService socialAccountService;
    private final ExperienceService experienceService;
    private final CertificationService certificationService;
    private final EducationService educationService;
    private final SkillService skillService;
    private final UserService userService;
    private final VoteService voteService;

    @Autowired
    public ResumeController(PostService postService, DepartmentService departmentService, NeedItemService needItemService, ResumeService resumeService, SocialAccountService socialAccountService, ExperienceService experienceService, CertificationService certificationService, EducationService educationService, SkillService skillService, UserService userService, VoteService voteService) {
        this.postService = postService;
        this.departmentService = departmentService;
        this.needItemService = needItemService;
        this.resumeService = resumeService;
        this.socialAccountService = socialAccountService;
        this.experienceService = experienceService;
        this.certificationService = certificationService;
        this.educationService = educationService;
        this.skillService = skillService;
        this.userService = userService;
        this.voteService = voteService;
    }

    ///resume/post?postId=1&departmentId=2
    @GetMapping("/post")
    public String getResume(@RequestParam("postId") Long postId, @RequestParam("departmentId") Long departmentId, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        Post post = postService.findById(postId);

        List<Resume> resumes = resumeService.findByPostId(postId);
        for (Resume resume : resumes) {
            if (Objects.equals(resume.getApplicantId(), loginUser.getUserId())) {
                model.addAttribute("alreadyApplied", true);
                return "redirect:/postlist";
            }
        }

        model.addAttribute("post", post);
        Department department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("userInfo", loginUser);
        model.addAttribute("needItems", needItemService.findByPostId(postId));
        return "resume/resumeView";
    }

    @PostMapping("/post")
    @Transactional
    public String submitResume(
            @RequestParam("postId") Long postId, @RequestParam("departmentId") Long departmentId,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("birthday") String birthday,
            @RequestParam("address") String address,
            @RequestParam("desc") String description,
            @RequestParam(value = "certification[]", required = false) List<String> certifications,
            @RequestParam(value = "cf_level[]", required = false) List<String> certificationLevels,
            @RequestParam(value = "cf_date[]", required = false) List<String> certificationDates,
            @RequestParam(value = "skill_stack[]", required = false) List<String> skillStacks,
            @RequestParam(value = "skill_level[]", required = false) List<LevelType> skillLevels,
            @RequestParam(value = "ex_company[]", required = false) List<String> experienceCompanies,
            @RequestParam(value = "ex_period[]", required = false) List<Integer> experiencePeriods,
            @RequestParam(value = "ex_work[]", required = false) List<String> experienceWorks,
            @RequestParam(value = "title[]", required = false) List<Long> titles,
            @RequestParam(value = "description[]", required = false) List<String> descriptions,
            HttpSession session,
            Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        Long resumeId = resumeService.save(new Resume(loginUser.getUserId(), postId, departmentId, ResumeStatusType.UNREAD, description));

        ResumeForm resumeForm = resumeService.makeResumeForm(resumeId, certifications, certificationLevels, certificationDates,
                skillStacks, skillLevels,
                experienceCompanies, experiencePeriods, experienceWorks,
                titles, descriptions);

        resumeService.saveResumeForm(resumeId, resumeForm);

        return "resume/resumeResult";
    }

    @GetMapping("/list")
    public String resumeList(@RequestParam("postId") Long postId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null || user.getRole().equals(RoleType.APPLICANT)) {
            return "redirect:/postlist/post?id=" + postId;
        }

        Post post = postService.findById(postId);

        List<ResumeUserResponse> resumes = resumeService.findResumeUserResponseByPostId(postId);
        model.addAttribute("resumes", resumes);
        model.addAttribute("post", post);
        model.addAttribute("department", departmentService.findById(post.getDepartmentId()));

        List<Vote> votes = new ArrayList<>();
        for (ResumeUserResponse resume : resumes) {
            votes.addAll(voteService.findByResumeId(resume.getResumeId()));
        }

        List<ResumeVoteResponse> resumeVoteResponses = resumeService.makeResumeVoteReponse(resumes, votes);

        model.addAttribute("resumeVoteResponses", resumeVoteResponses);

        return "resume/resumeList";
    }

    @GetMapping("/detail")
    public String resumeDetail(@RequestParam("postId") Long postId, @RequestParam("resumeId") Long resumeId, @RequestParam("userId") Long userId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null && user.getRole().equals(RoleType.ADMIN)) {
            return "redirect:/postlist/post?id=" + postId;
        }

        ResumeUserResponse resume = resumeService.findResumeUserResponseByResumeId(resumeId);
        if (resume.getStatus().equals(ResumeStatusType.UNREAD))
            resumeService.updateStatus(resumeId, ResumeStatusType.READ);

        model.addAttribute("resume", resume);
        model.addAttribute("socials", socialAccountService.findByUserId(userId));
        model.addAttribute("needItems", needItemService.findBydResumeId(resumeId));
        model.addAttribute("experiences", experienceService.findByResumeId(resumeId));
        model.addAttribute("certifications", certificationService.findByResumeId(resumeId));
        model.addAttribute("skills", skillService.findByResumeId(resumeId));
        model.addAttribute("educations", educationService.findByResumeId(resumeId));
        model.addAttribute("votes", voteService.findByResumeId(resumeId));

        return "resume/detailResume";
    }

    @PostMapping("/pass")
    public String passResume(@RequestParam("postId") Long postId, @RequestParam("resumeId") Long resumeId, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null || user.getRole().equals(RoleType.EMPLOYEE)) {
            return "redirect:/postlist/post?id=" + postId;
        }

        Resume resume = resumeService.findById(resumeId);

        resumeService.updateStatus(resume.getResumeId(), ResumeStatusType.PASS);
        userService.updateDepartmentByUserId(resume.getApplicantId(), resume.getDepartmentId());

        return "redirect:/resume/list?postId=" + postId;
    }

    @PostMapping("/fail")
    public String failResume(@RequestParam("postId") Long postId, @RequestParam("resumeId") Long resumeId, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null || user.getRole().equals(RoleType.EMPLOYEE)) {
            return "redirect:/postlist/post?id=" + postId;
        }

        Resume resume = resumeService.findById(resumeId);
        List<Department> defaultDept = departmentService.findByName("무소속");

        resumeService.updateStatus(resume.getResumeId(), ResumeStatusType.FAIL);
        userService.updateDepartmentByUserId(resume.getApplicantId(), defaultDept.get(0).getDepartmentId());

        return "redirect:/resume/list?postId=" + postId;
    }

    @PostMapping("/agree")
    public String agreeResume(@RequestParam("postId") Long postId, @RequestParam("resumeId") Long resumeId, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null || user.getRole().equals(RoleType.APPLICANT)) {
            return "redirect:/postlist/post?id=" + postId;
        }
        voteService.save(new Vote(1, user.getUserId(), resumeId));

        return "redirect:/resume/list?postId=" + postId;
    }

    @PostMapping("/disagree")
    public String diagreeResume(@RequestParam("postId") Long postId, @RequestParam("resumeId") Long resumeId, HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null || user.getRole().equals(RoleType.APPLICANT)) {
            return "redirect:/postlist/post?id=" + postId;
        }

        voteService.save(new Vote(0, user.getUserId(), resumeId));

        return "redirect:/resume/list?postId=" + postId;
    }
}
