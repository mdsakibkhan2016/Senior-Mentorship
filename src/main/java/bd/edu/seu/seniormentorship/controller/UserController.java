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
    public String registrationPage(Model model) {
        model.addAttribute("userList",userService.findAll());

        return "user-list";

    }
    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteById(id); // Your service method to delete by ID
        return "redirect:/user-list"; // Adjust path if needed
    }

















//    @GetMapping("/create-research-team")
//    public String createResearchTeamPage(Model model) {
//        model.addAttribute("create-research-team",studentService.findAll());
//
//        return "create-research-team";
//    }
}
