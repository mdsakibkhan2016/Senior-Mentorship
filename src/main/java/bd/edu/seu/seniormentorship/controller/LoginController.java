package bd.edu.seu.seniormentorship.controller;



import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {


    @GetMapping("/login")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "login";

    }

    @PostMapping("/submitLog")
    public String submitLog(@ModelAttribute User user, Model model) {
//        if (user != null) {
//            return "redirect:/dashboard";
//        }
//        model.addAttribute("user", user);
        return "redirect:/admindashboard";
    }
//    @PostMapping("/login/submit")
//    public String objectSubmitForm(@ModelAttribute Student s) {
//        System.out.println(s.getId() + " " + s.getName());
//
//        if (s.getEmail().equals("2022100000098@seu.edu.bd") && s.getPassowrd().equals("1234")) {
////            return "redirect:/admindashboard";
//            return "redirect:/StudentList";
//        }
//        return "redirect:/login?error=true";
//       // return "redirect:/login?error=true";
//        }
    }
