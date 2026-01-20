package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.service.ResearchService;

import bd.edu.seu.seniormentorship.service.ResearchTeamPredicitionService;
import bd.edu.seu.seniormentorship.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResearchController {
    private final StudentService studentService;
    private final ResearchService researchService;
    private final ResearchTeamPredicitionService researchTeamPredicitionService;
//    private final ResearchTeamOpenAIService researchTeamOpenAIService;

    public ResearchController(StudentService studentService, ResearchService researchService, ResearchTeamPredicitionService researchTeamPredicitionService) {
        this.studentService = studentService;
        this.researchService = researchService;

        this.researchTeamPredicitionService = researchTeamPredicitionService;
    }


    @GetMapping("/research")
    public String research(Model model) {
        model.addAttribute("researchTeamList", researchService.findAll());
        return "research-team";
    }

//    @PostMapping("/research/submit")
//    public String submitToResearch(@RequestParam("selectedStudentIds") List<String> studentIds, Model model) {
//        List<ResearchTeam> researchTeamList = new ArrayList<>();
//
//        for (String id : studentIds) {
//            Student student = studentService.getByStudentId(id); // fetch from DB
//            if (student != null) {
//                ResearchTeam rt = new ResearchTeam();
//                rt.setsId(student.getsId());
//                rt.setName(student.getName());
//                rt.setProgram(student.getProgram());
//                rt.setCgpa(student.getCgpa());
//
//                researchTeamList.add(rt);
//            }
//        }
//        List<ResearchTeam> researchTeams  = researchService.saveList(researchTeamList);
//
//        model.addAttribute("researchTeamList", researchTeams);
//        return "redirect:/research";
//    }


    @PostMapping("/research/submit")
    public String submitToResearch(@RequestParam("selectedStudentIds") List<String> studentIds) {
        List<ResearchTeam> researchTeamList = new ArrayList<>();

        for (String id : studentIds) {
            Student student = studentService.getByStudentId(id);
            if (student != null) {
                ResearchTeam rt = new ResearchTeam();
                rt.setsId(student.getsId());
                rt.setName(student.getName());
                rt.setProgram(student.getProgram());
                rt.setCgpa(student.getCgpa());
                researchTeamList.add(rt);

                // Student- mark  for  research team
                studentService.markAsInResearchTeam(student.getsId());
            }
        }

        researchService.saveList(researchTeamList);
        return "redirect:/student-list";
    }


    @PostMapping("/research/delete/{sId}")
    public String deleteFromResearch(@PathVariable String sId) {
        //  Research to delete
        researchService.deleteById(sId);

        // Student status false
        studentService.markAsNotInResearchTeam(sId);

//
        return "redirect:/research";
    }



}













