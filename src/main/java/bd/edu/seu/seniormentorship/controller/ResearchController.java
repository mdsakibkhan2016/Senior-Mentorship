package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.service.ResearchService;
import bd.edu.seu.seniormentorship.service.ResearchTeamGeminiService;
import bd.edu.seu.seniormentorship.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResearchController {

    private static final Logger log = LoggerFactory.getLogger(ResearchController.class);

    private final StudentService studentService;
    private final ResearchService researchService;
    private final ResearchTeamGeminiService researchTeamGeminiService;

    public ResearchController(StudentService studentService,
                              ResearchService researchService,
                              ResearchTeamGeminiService researchTeamGeminiService) {
        this.studentService            = studentService;
        this.researchService           = researchService;
        this.researchTeamGeminiService = researchTeamGeminiService;
    }

    /** Show all research team members */
    @GetMapping("/research")
    public String research(Model model) {
        model.addAttribute("researchTeamList", researchService.findAll());
        return "research-team";
    }

    /** Move selected students into research team */
    @PostMapping("/research/submit")
    public String submitToResearch(@RequestParam("selectedStudentIds") List<String> studentIds) {
        List<ResearchTeam> list = new ArrayList<>();
        for (String id : studentIds) {
            Student student = studentService.getByStudentId(id);
            if (student != null) {
                ResearchTeam rt = new ResearchTeam();
                rt.setsId(student.getsId());
                rt.setName(student.getName());
                rt.setProgram(student.getProgram());
                rt.setCgpa(student.getCgpa());
                list.add(rt);
                studentService.markAsInResearchTeam(student.getsId());
            }
        }
        researchService.saveList(list);
        return "redirect:/student-list";
    }

    /** Remove a student from research team */
    @PostMapping("/research/delete/{sId}")
    public String deleteFromResearch(@PathVariable String sId) {
        researchService.deleteById(sId);
        studentService.markAsNotInResearchTeam(sId);
        return "redirect:/research";
    }

    /**
     * GET /research/ai-teams
     * Calls Gemini AI to generate balanced teams of 3 by CGPA.
     */
    @GetMapping("/research/ai-teams")
    public String generateAiTeams(Model model) {
        try {
            List<List<ResearchTeam>> teams = researchTeamGeminiService.generateTeams();
            model.addAttribute("aiTeams", teams);
            model.addAttribute("errorMessage", null);
        } catch (Exception e) {
            log.error("Gemini AI team generation failed", e);
            model.addAttribute("aiTeams", List.of());
            model.addAttribute("errorMessage", "AI team generation failed: " + e.getMessage());
        }
        return "ai-teams";
    }
}
