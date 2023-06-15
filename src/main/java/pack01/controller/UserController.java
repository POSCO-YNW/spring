package pack01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pack01.domain.User;
import pack01.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user/user-list";
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
}