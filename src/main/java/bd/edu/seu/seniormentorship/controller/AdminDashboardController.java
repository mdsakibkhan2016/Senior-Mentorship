package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminDashboardController {
    @GetMapping("/admindashboard")
    public String registrationPage(Model model) {
        model.addAttribute("student", new Student());

        return "admindashboard";
    }
}
