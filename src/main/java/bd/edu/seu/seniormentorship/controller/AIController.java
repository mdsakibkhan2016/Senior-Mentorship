package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.service.ResearchTeamPredicitionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AIController {

   private final ResearchTeamPredicitionService researchTeamPredicitionService;

    public AIController(ResearchTeamPredicitionService researchTeamPredicitionService) {
        this.researchTeamPredicitionService = researchTeamPredicitionService;
    }

    @GetMapping("/aiTeams")
    public String registrationPage(Model model) {
        model.addAttribute("student", new Student());

        String aiResponse =  researchTeamPredicitionService.predictBalancedTeams();
        model.addAttribute("teamGroups", aiResponse);
        return "ai-teams";
    }
}
