package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pack01.domain.User;
import pack01.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String showLoginPage() {
        return "login";
    }

    @PostMapping
    public String processLogin(String email, String password, HttpSession session, RedirectAttributes redirectAttributes) {
        User loginUser = userService.findByEmailAndPassword(email, password);
        if (loginUser != null) {
            session.setAttribute("loginUser", loginUser);

            redirectAttributes.addFlashAttribute("message", "로그인에 성공했습니다.");
            return "redirect:/user-list";
        } else {
            redirectAttributes.addFlashAttribute("error", "아이디 또는 패스워드가 틀렸습니다.");
            return "redirect:/login";
        }
    }

}