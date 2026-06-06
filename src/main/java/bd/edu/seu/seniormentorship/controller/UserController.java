package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-list")
    public String userList(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "user-list";
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return "redirect:/user-list";
    }
}
