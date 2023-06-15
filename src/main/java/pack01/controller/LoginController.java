package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack01.domain.Department;
import pack01.domain.User;
import pack01.domain.type.RoleType;
import pack01.service.DepartmentService;
import pack01.service.UserService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {

    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    public LoginController(UserService userService, DepartmentService departmentService) {
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @GetMapping("/login")
    public String showLoginPage(HttpSession session) {
        if (session.getAttribute("loginUser") != null) {
            return "redirect:/postlist";
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

    @GetMapping("/signup")
    public String showSignupPage(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") != null) {
            return "redirect:/postlist";
        }

        List<RoleType> roleTypes = Arrays.asList(RoleType.values());
        model.addAttribute("roleTypes", roleTypes);

        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@RequestParam("username") String username,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("birthday") String birthday,
                                @RequestParam("address") String address,
                                @RequestParam("role") RoleType role,
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

        User user = new User(username, password, email, sqlDate, role, address, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), null);

        Long saveId = userService.save(user);

        if (saveId != null) {
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("message", "중복되는 아이디가 있습니다.");
            return "redirect:/signup";
        }
    }
}