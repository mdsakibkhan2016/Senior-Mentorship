package bd.edu.seu.seniormentorship.controller;


import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.model.User;

import bd.edu.seu.seniormentorship.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          RegistrationController {



    private final UserService userService;

    public RegistrationController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
       // model.addAttribute("student", new Student());
        model.addAttribute("user", new User());

        return "registration";

    }

    @PostMapping("/submit")
    public String objectSubmitForm(@ModelAttribute User user) {
        System.out.println(user.getId() + " " + user.getName());

         userService.create(user);
        return "login";
    }
}

