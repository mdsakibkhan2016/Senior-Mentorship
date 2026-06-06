package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/submitLog")
    public String submitLog(@ModelAttribute User user) {
        return "redirect:/admindashboard";
    }
}
