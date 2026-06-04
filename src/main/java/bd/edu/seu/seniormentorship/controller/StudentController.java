package bd.edu.seu.seniormentorship.controller;


import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.repository.ResearchTeamRepo;
import bd.edu.seu.seniormentorship.service.ResearchService;
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
public class StudentController {


    private final StudentService studentService;
    private final ResearchService researchService;

    public StudentController(StudentService studentService, ResearchService researchService) {
        this.studentService = studentService;
        this.researchService = researchService;
    }

    @GetMapping("/student-list")
    public String registrationPage(Model model) {
       model.addAttribute("studentList",studentService.findAll());
       model.addAttribute("studentForm", new Student());
        return "student-list";

    }

//     @PostMapping("/submitStudent")
//        public String submitStudent(Model model, Student student) {
//        studentService.save(student);
//        return "redirect:/student-list";
//        }

//    New
//add student error
    @PostMapping("/submitStudent")
    public String submitStudent(Model model, Student student) {
        // Check if student with same sId already exists
        Student existing = studentService.getByStudentId(student.getsId());
        if (existing != null) {
            // sId already exists, so return with error
            model.addAttribute("errorMessage", "Student ID already exists. Please enter a unique ID.");
            model.addAttribute("studentList", studentService.findAll());
            model.addAttribute("studentForm", student);
            return "student-list";
        }
        studentService.save(student);
        return "redirect:/student-list";
    }



    @PostMapping("/students/delete/{sId}")
    public String deleteResearchStudent(@PathVariable String sId) {
        studentService.deleteById(sId);
        return "redirect:/student-list";
    }
    @PostMapping("/students/update")
    public String updateStudent(Student student) {
        studentService.save(student); // save() will update if sId exists
        return "redirect:/student-list";
    }

//    search student

//    @GetMapping("/student/search")
//    public String searchStudentById(@RequestParam("keyword") String keyword, Model model) {
//        Student student = studentService.getByStudentId(keyword);
//        List<Student> searchResults = new ArrayList<>();
//        if (student != null) {
//            searchResults.add(student); // একটাই পাবে ID অনুযায়ী
//        }
//        model.addAttribute("studentList", searchResults);
//        model.addAttribute("studentForm", new Student()); // Add modal এর জন্য
//        return "student-list"; // student-list.html এর নাম যেটা তোমার মূল টেমপ্লেট
//    }

//        search student jodi na pay error dibe
@GetMapping("/student/search")
public String searchStudentById(@RequestParam("keyword") String keyword, Model model) {
    Student student = studentService.getByStudentId(keyword);
    List<Student> studentList;

    if (student != null) {
        // student only
        studentList = new ArrayList<>();
        studentList.add(student);
    } else {
        // student all list
        studentList = studentService.findAll();
        model.addAttribute("searchMessage", "No student found with your ID: " + keyword);
    }

    model.addAttribute("studentList", studentList);
    model.addAttribute("studentForm", new Student()); // new form reset
    return "student-list";
}










//    @GetMapping("/create-research-team")
//    public String createResearchTeamPage(Model model) {
//        model.addAttribute("create-research-team",studentService.findAll());
//
//        return "create-research-team";
//    }
}
