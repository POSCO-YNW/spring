package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack01.domain.User;
import pack01.domain.type.RoleType;
import pack01.dto.user.response.UserDepartmentResponse;
import pack01.service.UserService;
import pack01.service.VoteService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final VoteService voteService;

    @Autowired
    public UserController(UserService userService, VoteService voteService) {
        this.userService = userService;
        this.voteService = voteService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/user-details";
    }

    @GetMapping("/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/create-user";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserForm(@PathVariable("id") Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "user/edit-user";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }

    @GetMapping("/manage")
    public String userManage(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!loginUser.getRole().equals(RoleType.ADMIN)) {
            return "redirect:/postlist";
        }

        List<UserDepartmentResponse> users = userService.findByRole(RoleType.EMPLOYEE, loginUser.getDepartmentId());

        model.addAttribute("users", users);

        return "user/userList";
    }

    @GetMapping("/manage/delete")
    public String deleteUser(@RequestParam("userId") Long userId, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!loginUser.getRole().equals(RoleType.ADMIN)) {
            return "redirect:/postlist";
        }

        voteService.deleteByUserId(userId);

        userService.delete(userId);

        return "redirect:/user/manage";
    }
}