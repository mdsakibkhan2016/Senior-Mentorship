package bd.edu.seu.seniormentorship.service;


import bd.edu.seu.seniormentorship.model.ResearchTeam;
import bd.edu.seu.seniormentorship.model.Student;
import bd.edu.seu.seniormentorship.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void save(Student student) {
        studentRepository.save(student);
    }

    public Student getByStudentId(String sId) {
        Optional<Student> sOptional = studentRepository.findBysId(sId);
        return sOptional.orElse(null);
    }

    public List<Student> findAll() {
        return studentRepository.findByInResearchTeamFalse(); // reasearch page to delete recharge e nai
    }


    public void deleteById(String sId){
        Optional<Student> optionalStudent = studentRepository.findBysId(sId);
        optionalStudent.ifPresent(student -> studentRepository.deleteById(student.getId())); //mongo delete
    }

    //for 2 update
    public void update(Student student) {
        Optional<Student> existing = studentRepository.findById(student.getId());
        if (existing.isPresent()) {
            Student s = existing.get();
            s.setName(student.getName());
            s.setProgram(student.getProgram());
            s.setCgpa(student.getCgpa());
            studentRepository.save(s); //
        }
    }

    public void markAsInResearchTeam(String sId) {
        Optional<Student> optional = studentRepository.findBysId(sId);
        optional.ifPresent(student -> {
            student.setInResearchTeam(true);
            studentRepository.save(student);
        });
    }

    public void markAsNotInResearchTeam(String sId) {
        Optional<Student> optional = studentRepository.findBysId(sId);
        optional.ifPresent(student -> {
            student.setInResearchTeam(false);
            studentRepository.save(student);
        });
    }




}
