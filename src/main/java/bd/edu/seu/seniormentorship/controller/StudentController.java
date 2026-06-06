package bd.edu.seu.seniormentorship.controller;

import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student-list")
    public String studentList(Model model) {
        model.addAttribute("studentList", studentService.findAll());
        model.addAttribute("studentForm", new Student());
        return "student-list";
    }

    @PostMapping("/submitStudent")
    public String submitStudent(Student student, Model model) {
        Student existing = studentService.getByStudentId(student.getsId());
        if (existing != null) {
            model.addAttribute("errorMessage", "Student ID already exists. Please enter a unique ID.");
            model.addAttribute("studentList", studentService.findAll());
            model.addAttribute("studentForm", student);
            return "student-list";
        }
        studentService.save(student);
        return "redirect:/student-list";
    }

    @PostMapping("/students/delete/{sId}")
    public String deleteStudent(@PathVariable String sId) {
        studentService.deleteById(sId);
        return "redirect:/student-list";
    }

    @PostMapping("/students/update")
    public String updateStudent(Student student) {
        studentService.update(student);
        return "redirect:/student-list";
    }

    @GetMapping("/student/search")
    public String searchStudent(@RequestParam("keyword") String keyword, Model model) {
        Student student = studentService.getByStudentId(keyword);
        List<Student> studentList;

        if (student != null) {
            studentList = new ArrayList<>();
            studentList.add(student);
        } else {
            studentList = studentService.findAll();
            model.addAttribute("searchMessage", "No student found with ID: " + keyword);
        }

        model.addAttribute("studentList", studentList);
        model.addAttribute("studentForm", new Student());
        return "student-list";
    }
}
