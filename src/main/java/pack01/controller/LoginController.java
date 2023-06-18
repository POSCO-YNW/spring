package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack01.domain.Department;
import pack01.domain.SocialAccount;
import pack01.domain.User;
import pack01.domain.type.RoleType;
import pack01.domain.type.SocialType;
import pack01.service.DepartmentService;
import pack01.service.SocialAccountService;
import pack01.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static pack01.common.StaticValue.*;

@Controller
@RequestMapping
public class LoginController {

    private final UserService userService;
    private final DepartmentService departmentService;
    private final SocialAccountService socialAccountService;

    @Autowired
    public LoginController(UserService userService, DepartmentService departmentService, SocialAccountService socialAccountService) {
        this.userService = userService;
        this.departmentService = departmentService;
        this.socialAccountService = socialAccountService;
    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            return "redirect:/postlist";
        }

        //어드민 계정 생성
        List<Department> developDepartment = departmentService.findByName(DEVELOP_DEPARTMENT_NAME);
        List<Department> hrDepartment = departmentService.findByName(HR_DEPARTMENT_NAME);

        if (developDepartment.size() == 0 && hrDepartment.size() == 0) {
            Long dev = departmentService.save(new Department(DEVELOP_DEPARTMENT_NAME, DEVELOP_DEPARTMENT_TELEPHONE_NUMBER, "", DEVELOP_DEPARTMENT_LOCATION, 37.403671, 127.103126));
            Long hr = departmentService.save(new Department(HR_DEPARTMENT_NAME, HR_DEPARTMENT_TELEPHONE_NUMBER, "", HR_DEPARTMENT_LOCATION, 36.004516, 129.395506));
            Long construction = departmentService.save(new Department(CONSTRUCTION_DEPARTMENT_NAME, CONSTRUCTION_DEPARTMENT_TELEPHONE_NUMBER, "", CONSTRUCTION_DEPARTMENT_LOCATION, 37.392346, 126.634134));

            userService.save(new User(DEVELOP_ADMIN_NAME, DEVELOP_ADMIN_PASSWORD, DEVELOP_ADMIN_EMAIL, "010-0000-1111", Date.valueOf(LocalDate.now()), RoleType.ADMIN, DEVELOP_DEPARTMENT_LOCATION, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), dev));
            userService.save(new User(HR_ADMIN_NAME, HR_ADMIN_PASSWORD, HR_ADMIN_EMAIL, "010-0000-2222", Date.valueOf(LocalDate.now()), RoleType.ADMIN, HR_DEPARTMENT_LOCATION, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), hr));
            userService.save(new User(CONSTRUCTION_ADMIN_NAME, CONSTRUCTION_ADMIN_PASSWORD, CONSTRUCTION_ADMIN_EMAIL, "010-1111-1111", Date.valueOf(LocalDate.now()), RoleType.ADMIN, CONSTRUCTION_DEPARTMENT_LOCATION, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), construction));
        }

        return "login";
    }

    @PostMapping("/login")
    public String processLogin(String email, String password, HttpSession session, RedirectAttributes redirectAttributes) {
        User loginUser = userService.findByEmailAndPassword(email, password);
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);

            redirectAttributes.addFlashAttribute("message", "로그인에 성공했습니다.");
            return "redirect:/postlist";
        } else {
            redirectAttributes.addFlashAttribute("error", "아이디 또는 패스워드가 틀렸습니다.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/postlist";
    }

    @GetMapping("/signup")
    public String showSignupPage(@RequestParam(value = "deptKey", required = false) String deptKey, HttpSession session, Model model) {
        if (session.getAttribute("loginUser") != null) {
            return "redirect:/postlist";
        }

        List<RoleType> roleTypes = Arrays.asList(RoleType.values());
        model.addAttribute("roleTypes", roleTypes);

        if (deptKey != null) {
            Department department = departmentService.findByKey(deptKey);
            model.addAttribute("department", department);
        }

        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("phoneNumber") String phoneNumber,
                                @RequestParam("birthday") String birthday,
                                @RequestParam("address") String address,
                                @RequestParam("role") RoleType role,
                                @RequestParam(value = "deptId", required = false) Long deptId,
                                @RequestParam(value = "social[]", required = false) List<String> socials,
                                RedirectAttributes redirectAttributes) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate;
        try {
            parsedDate = dateFormat.parse(birthday);
        } catch (ParseException e) {
            redirectAttributes.addFlashAttribute("message", "유효하지 않은 생년월일 형식입니다.");
            return "redirect:/signup";
        }

        Date sqlDate = new Date(parsedDate.getTime());
        User user = new User();

        if (deptId >= 0) {
            user = new User(username, password, email, phoneNumber, sqlDate, role, address, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), deptId);
        } else {
            user = new User(username, password, email, phoneNumber, sqlDate, role, address, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null);
        }
        Long saveId = userService.save(user);

        if (saveId == null) {
            redirectAttributes.addFlashAttribute("message", "중복되는 아이디가 있습니다.");
            return "redirect:/signup";
        }

        for (int i = 0; i < socials.size(); i++) {
            switch (i) {
                case 0:
                    socialAccountService.save(new SocialAccount(SocialType.GITHUB, socials.get(i), "https://github.com/" + socials.get(i), saveId));
                    break;
                case 1:
                    socialAccountService.save(new SocialAccount(SocialType.TISTORY, socials.get(i), "https://" + socials.get(i) + ".tistory.com/", saveId));
                    break;
                case 2:
                    socialAccountService.save(new SocialAccount(SocialType.BOJ, socials.get(i), "https://www.acmicpc.net/user/" + socials.get(i), saveId));
                    break;
            }

        }

        return "redirect:/login";
    }
}