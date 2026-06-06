package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.model.User;
import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.service.UserService;
import bd.edu.seu.seniormentorship.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final StudentService studentService;

    public RegistrationController(UserService userService, StudentService studentService) {
        this.userService = userService;
        this.studentService = studentService;
    }

    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute User user,
                             @RequestParam String program,
                             @RequestParam double cgpa) {
        // Create User account
        userService.create(user);

        // Create corresponding Student profile for research purposes
        Student student = new Student(user.getId(), user.getName(), program, cgpa);
        studentService.save(student);

        return "redirect:/login";
    }
}
