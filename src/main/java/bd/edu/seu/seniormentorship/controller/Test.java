package bd.edu.seu.seniormentorship.controller;


import bd.edu.seu.seniormentorship.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {
    @GetMapping("/test")
    public String TestPage(Model model) {
        return "test";
    }

    @GetMapping("/test2")
    public String TestPage1(Model model) {
        return "test2";
    }
}
